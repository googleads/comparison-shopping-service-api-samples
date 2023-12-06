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

package shopping.css.samples.v1.accountlabels;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.shopping.css.v1.AccountLabel;
import com.google.shopping.css.v1.AccountLabelsServiceClient;
import com.google.shopping.css.v1.AccountLabelsServiceClient.ListAccountLabelsPagedResponse;
import com.google.shopping.css.v1.AccountLabelsServiceSettings;
import com.google.shopping.css.v1.ListAccountLabelsRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to list AccountLabels for a given parent account */
public class ListAccountLabels {

  private static String getName(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  // [START list_account_label]
  public static void listAccountLabels(Config config) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    AccountLabelsServiceSettings accountLabelsServiceSettings =
        AccountLabelsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String parent = getName(config.getDomainId().toString());
    try (AccountLabelsServiceClient accountLabelsServiceClient =
        AccountLabelsServiceClient.create(accountLabelsServiceSettings)) {

      ListAccountLabelsRequest request =
          ListAccountLabelsRequest.newBuilder().setParent(parent).build();

      System.out.println("Sending ListAccountLabels request");
      ListAccountLabelsPagedResponse response =
          accountLabelsServiceClient.listAccountLabels(request);
      System.out.println("Retrieved AccountLabels: ");

      int count = 0;

      // Iterates over all rows in all pages and prints the element in each row
      for (AccountLabel element : response.iterateAll()) {
        System.out.println(element);
        count++;
      }
      System.out.print("The following count of elements were returned: ");
      System.out.println(count);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END list_account_label]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    listAccountLabels(config);
  }
}
