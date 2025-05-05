// Copyright 2025 Google LLC
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

package shopping.css.samples.v1.quota;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.shopping.css.v1.ListQuotaGroupsRequest;
import com.google.shopping.css.v1.QuotaGroup;
import com.google.shopping.css.v1.QuotaServiceClient;
import com.google.shopping.css.v1.QuotaServiceClient.ListQuotaGroupsPagedResponse;
import com.google.shopping.css.v1.QuotaServiceSettings;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to list quota groups for a given Account */
public class ListQuotaGroups {

  private static String getParent(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  // [START merchantapi_css_list_quota_groups]
  public static void listQuotaGroups(Config config) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    QuotaServiceSettings quotaServiceSettings =
        QuotaServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String parent = getParent(config.getDomainId().toString());

    try (QuotaServiceClient quotaServiceClient = QuotaServiceClient.create(quotaServiceSettings)) {

      ListQuotaGroupsRequest request =
          ListQuotaGroupsRequest.newBuilder().setParent(parent).build();

      System.out.println("Sending ListQuotaGroups request");
      ListQuotaGroupsPagedResponse response = quotaServiceClient.listQuotaGroups(request);
      System.out.println("Retrieved QuotaGroups: ");

      int count = 0;

      // Iterates over all rows in all pages and prints the element in each row
      for (QuotaGroup element : response.iterateAll()) {
        System.out.println(element);
        count++;
      }
      System.out.print("The following count of elements were returned: ");
      System.out.println(count);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    listQuotaGroups(config);
  }
  // [END merchantapi_css_list_quota_groups]
}
