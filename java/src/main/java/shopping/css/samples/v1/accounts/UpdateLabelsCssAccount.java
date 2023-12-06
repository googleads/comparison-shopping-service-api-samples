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
import com.google.shopping.css.v1.UpdateAccountLabelsRequest;
import java.util.Arrays;
import java.util.List;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to update AccountLabels for a given CSS Account */
public class UpdateLabelsCssAccount {

  private static String getName(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  // [START update_labels_css_account]
  public static void updateLabelsCssAccount(Config config, List<Long> labelIds) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    AccountsServiceSettings accountsServiceSettings =
        AccountsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String name = getName(config.getDomainId().toString());

    try (AccountsServiceClient accountsServiceClient =
        AccountsServiceClient.create(accountsServiceSettings)) {

      UpdateAccountLabelsRequest request =
          UpdateAccountLabelsRequest.newBuilder().setName(name).addAllLabelIds(labelIds).build();

      System.out.println("Sending UpdateLabels request");
      Account response = accountsServiceClient.updateLabels(request);
      System.out.println("Updated Account below");
      System.out.println(response);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END update_labels_css_account]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();
    // The AccountLabel IDs to be assigned to the CSS account. Leave the list empty to delete all
    // assigned labels
    List<Long> labelIds = Arrays.asList(10000123456L, 10000123457L);

    updateLabelsCssAccount(config, labelIds);
  }
}
