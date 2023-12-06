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
import com.google.shopping.css.v1.AccountLabelsServiceSettings;
import com.google.shopping.css.v1.UpdateAccountLabelRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to update an AccountLabel for a given parent account */
public class UpdateAccountLabel {

  private static String getName(String accountId, String labelId) {
    return String.format("accounts/%s/labels/%s", accountId, labelId);
  }

  // [START update_account_label]
  public static void updateAccountLabel(Config config, String labelId) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    AccountLabelsServiceSettings accountLabelsServiceSettings =
        AccountLabelsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String name = getName(config.getDomainId().toString(), labelId);

    try (AccountLabelsServiceClient accountLabelsServiceClient =
        AccountLabelsServiceClient.create(accountLabelsServiceSettings)) {

      UpdateAccountLabelRequest request =
          UpdateAccountLabelRequest.newBuilder()
              .setAccountLabel(
                  AccountLabel.newBuilder()
                      .setName(name)
                      .setDisplayName("Updated Display Name")
                      .setDescription("Updated Description")
                      .build())
              .build();

      System.out.println("Sending UpdateAccountLabel request");
      AccountLabel response = accountLabelsServiceClient.updateAccountLabel(request);
      System.out.println("Updated AccountLabel below");
      System.out.println(response);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END update_account_label]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();
    // The ID of the AccountLabel to be updated
    final String labelId = "10000123456";

    updateAccountLabel(config, labelId);
  }
}
