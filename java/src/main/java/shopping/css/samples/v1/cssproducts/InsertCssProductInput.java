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
import com.google.shopping.css.v1.CssProductInput;
import com.google.shopping.css.v1.CssProductInputsServiceClient;
import com.google.shopping.css.v1.CssProductInputsServiceSettings;
import com.google.shopping.css.v1.InsertCssProductInputRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to insert a CSS Product for a given Account */
public class InsertCssProductInput {

  private static String getParent(String domainId) {
    return String.format("accounts/%s", domainId);
  }

  private static String getName(String domainId, String productId) {
    return String.format("accounts/%s/cssProductInputs/%s", domainId, productId);
  }

  // [START insert_css_product_input]
  public static void insertCssProductInput(Config config, long feedId, String rawProvidedId)
      throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    CssProductInputsServiceSettings cssProductInputsServiceSettings =
        CssProductInputsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String parent = getParent(config.getDomainId().toString());

    try (CssProductInputsServiceClient cssProductInputsServiceClient =
        CssProductInputsServiceClient.create(cssProductInputsServiceSettings)) {

      InsertCssProductInputRequest request =
          InsertCssProductInputRequest.newBuilder()
              .setParent(parent)
              .setCssProductInput(
                  CssProductInput.newBuilder()
                      .setRawProvidedId(rawProvidedId)
                      .setFeedLabel("DE")
                      .setContentLanguage("de")
                      .setAttributes(
                          com.google.shopping.css.v1.Attributes.newBuilder()
                              .setTitle("Attribute Title")
                              .setHeadlineOfferLink("abc.com")
                              .setHeadlineOfferCondition("New")
                              .setDescription("CSS Product description 0")
                              .setNumberOfOffers(123)
                              .setCppLink("cpp_link.com")
                              .setBrand("CSS brand")
                              .setGoogleProductCategory("Media > Books")
                              .setGtin("3614030018941")
                              .setHeadlineOfferPrice(
                                  com.google.shopping.type.Price.newBuilder().build())
                              .build())
                      .build())
              .setFeedId(feedId)
              .build();

      System.out.println("Sending InsertCssProductInput request");
      CssProductInput response = cssProductInputsServiceClient.insertCssProductInput(request);
      System.out.println("Inserted CssProduct Name below");
      System.out.println(response.getName());
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END insert_css_product]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();
    // The ID uniquely identifying each feed
    final long feedId = 0;

    // Create a thread pool to insert multiple CSS Products in parallel
    ExecutorService threadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 100; i++) {
      // The raw ID identifying each product
      final String rawProvidedId = "rawProvidedId" + i;
      threadPool.execute(
          () -> {
            try {
              insertCssProductInput(config, feedId, rawProvidedId);
            } catch (Exception e) {
              System.out.println(e);
            }
          });
    }
  }
}
