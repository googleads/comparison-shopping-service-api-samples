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
import com.google.shopping.css.v1.CssProductsServiceSettings;
import com.google.shopping.css.v1.GetCssProductRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to get a CSS Product for a given Account */
public class GetCssProduct {

  private static String getName(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  private static String getName(String domainId, String productId) {
    return String.format("accounts/%s/cssProducts/%s", domainId, productId);
  }

  // [START get_css_product]
  public static void getCssProduct(Config config, String productId) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    CssProductsServiceSettings cssProductsServiceSettings =
        CssProductsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String name = getName(config.getDomainId().toString(), productId);

    try (CssProductsServiceClient cssProductsServiceClient =
        CssProductsServiceClient.create(cssProductsServiceSettings)) {

      GetCssProductRequest request = GetCssProductRequest.newBuilder().setName(name).build();

      System.out.println("Sending GetCssProduct request");
      CssProduct response = cssProductsServiceClient.getCssProduct(request);
      System.out.println("Retrieved CssProduct Name below");
      System.out.println(response.getName());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END get_css_product]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    getCssProduct(config, "it~IT~12345");
  }
}
