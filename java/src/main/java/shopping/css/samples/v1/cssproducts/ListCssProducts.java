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

package shopping.css.samples.v1.cssproducts;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.shopping.css.v1.CssProduct;
import com.google.shopping.css.v1.CssProductsServiceClient;
import com.google.shopping.css.v1.CssProductsServiceClient.ListCssProductsPagedResponse;
import com.google.shopping.css.v1.CssProductsServiceSettings;
import com.google.shopping.css.v1.ListCssProductsRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to list CSS Products for a given Account */
public class ListCssProducts {

  private static String getParent(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  private static String getName(String domainId, String productId) {
    return String.format("accounts/%s/cssProducts/%s", domainId, productId);
  }

  // [START list_css_products]
  public static void listCssProducts(Config config) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    CssProductsServiceSettings cssProductsServiceSettings =
        CssProductsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String parent = getParent(config.getDomainId().toString());

    try (CssProductsServiceClient cssProductsServiceClient =
        CssProductsServiceClient.create(cssProductsServiceSettings)) {

      ListCssProductsRequest request =
          ListCssProductsRequest.newBuilder().setParent(parent).build();

      System.out.println("Sending ListCssProducts request");
      ListCssProductsPagedResponse response = cssProductsServiceClient.listCssProducts(request);
      System.out.println("Retrieved CssProduct Names: ");

      int count = 0;

      // Iterates over all rows in all pages and prints the element in each row
      for (CssProduct element : response.iterateAll()) {
        System.out.println(element);
        count++;
      }
      System.out.print("The following count of elements were returned: ");
      System.out.println(count);
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END list_css_products]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    listCssProducts(config);
  }
}
