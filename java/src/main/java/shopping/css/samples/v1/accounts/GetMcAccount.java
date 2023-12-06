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
import com.google.shopping.css.v1.AccountsServiceSettings;
import com.google.shopping.css.v1.GetAccountRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to get an MC Account */
public class GetMcAccount {

  private static String getName(String accountId) {
    return String.format("accounts/%s", accountId);
  }

  // [START get_mc_account]
  public static void getMcAccount(Config config) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    AccountsServiceSettings accountsServiceSettings =
        AccountsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String name = getName(config.getMerchantId().toString());
    String parent = getName(config.getDomainId().toString());

    try (AccountsServiceClient accountsServiceClient =
        AccountsServiceClient.create(accountsServiceSettings)) {

      GetAccountRequest request =
          GetAccountRequest.newBuilder().setName(name).setParent(parent).build();

      System.out.println("Sending GetAccount request");
      Account response = accountsServiceClient.getAccount(request);
      System.out.println("Retrieved MC Account below");
      System.out.println(response);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END get_mc_account]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    getMcAccount(config);
  }
}
