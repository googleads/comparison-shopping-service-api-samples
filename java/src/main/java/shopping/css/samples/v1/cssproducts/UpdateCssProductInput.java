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
import com.google.protobuf.FieldMask;
import com.google.shopping.css.v1.CssProductInput;
import com.google.shopping.css.v1.CssProductInputsServiceClient;
import com.google.shopping.css.v1.CssProductInputsServiceSettings;
import com.google.shopping.css.v1.UpdateCssProductInputRequest;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to update a CSS Product for a given Account */
public class UpdateCssProductInput {

  private static String getName(String domainId, String productId) {
    return String.format("accounts/%s/cssProductInputs/%s", domainId, productId);
  }

  // [START update_css_product_input]
  public static void updateCssProductInput(Config config, String productId) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();
    String name = getName(config.getDomainId().toString(), productId);

    CssProductInputsServiceSettings cssProductInputsServiceSettings =
        CssProductInputsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    try (CssProductInputsServiceClient cssProductInputsServiceClient =
        CssProductInputsServiceClient.create(cssProductInputsServiceSettings)) {

      // Updates the title of the CSS Product leaving the rest of the fields unchanged
      UpdateCssProductInputRequest request =
          UpdateCssProductInputRequest.newBuilder()
              .setCssProductInput(
                  CssProductInput.newBuilder()
                      .setName(name)
                      .setAttributes(
                          com.google.shopping.css.v1.Attributes.newBuilder()
                              .setTitle("Attribute Title")
                              .setHeadlineOfferLink("abc.com")
                              .setHeadlineOfferCondition("New")
                              .setDescription("CSS Product description 0")
                              .build())
                      .build())
              .setUpdateMask(FieldMask.newBuilder().addPaths("title").build())
              .build();

      System.out.println("Updating CSS Product");
      CssProductInput response = cssProductInputsServiceClient.updateCssProductInput(request);
      System.out.print("CSS product updated:");

    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END update_css_product]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    // The ID uniquely identifying each product. In
    // the format languageCode~countryCode~rawProvidedId
    final String productId = "de~DE~rawProvidedId17";
    updateCssProductInput(config, productId);
  }
}