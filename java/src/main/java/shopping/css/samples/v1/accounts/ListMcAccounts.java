// Copyright 2023 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package shopping.css.samples.v1.accounts;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.shopping.css.v1.Account;
import com.google.shopping.css.v1.AccountsServiceClient;
import com.google.shopping.css.v1.AccountsServiceClient.ListChildAccountsPagedResponse;
import com.google.shopping.css.v1.AccountsServiceSettings;
import com.google.shopping.css.v1.ListChildAccountsRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to list the child (MC) Accounts of a given CSS domain Account */
public class ListMcAccounts {

  private static String getParent(String accountId) {
    return String.format("accounts/%s", accountId);
  }

  // [START list_mc_accounts]
  public static void listMcAccounts(Config config) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    AccountsServiceSettings accountsServiceSettings =
        AccountsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String parent = getParent(config.getDomainId().toString());

    try (AccountsServiceClient accountsServiceClient =
        AccountsServiceClient.create(accountsServiceSettings)) {

      ListChildAccountsRequest request =
          ListChildAccountsRequest.newBuilder().setParent(parent).build();

      System.out.println("Sending ListChildAccounts request");
      ListChildAccountsPagedResponse response = accountsServiceClient.listChildAccounts(request);
      System.out.println("Retrieved Accounts: ");

      int count = 0;

      // Iterates over all rows in all pages and prints the element in each row
      for (Account element : response.iterateAll()) {
        System.out.println(element);
        count++;
      }
      System.out.print("The following count of elements were returned: ");
      System.out.println(count);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END list_mc_accounts]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    listMcAccounts(config);
  }
}
