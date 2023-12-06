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
import com.google.shopping.css.v1.CssProductInputName;
import com.google.shopping.css.v1.CssProductInputsServiceClient;
import com.google.shopping.css.v1.CssProductInputsServiceSettings;
import com.google.shopping.css.v1.DeleteCssProductInputRequest;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import shopping.css.samples.utils.Authenticator;
import shopping.css.samples.utils.Config;

/** This class demonstrates how to delete a CSS Product */
public class DeleteCssProductInput {

  // [START delete_css_product_input]
  public static void deleteCssProductInput(Config config, String productId) throws Exception {
    GoogleCredentials credential = new Authenticator().authenticate();

    CssProductInputsServiceSettings cssProductInputsServiceSettings =
        CssProductInputsServiceSettings.newBuilder()
            .setCredentialsProvider(FixedCredentialsProvider.create(credential))
            .build();

    String name =
        CssProductInputName.newBuilder()
            .setAccount(config.getDomainId().toString())
            .setCssProductInput(productId)
            .build()
            .toString();

    try (CssProductInputsServiceClient cssProductInputsServiceClient =
        CssProductInputsServiceClient.create(cssProductInputsServiceSettings)) {
      DeleteCssProductInputRequest request =
          DeleteCssProductInputRequest.newBuilder().setName(name).build();

      System.out.println("Sending DeleteCssProductInput request");
      cssProductInputsServiceClient.deleteCssProductInput(
          request); // no response returned on success
      System.out.println(
          "Delete successful, note that it may take up to 30 minutes for the delete to update in"
              + " the system.");
    } catch (Exception e) {
      System.out.println(e);
    }
  }

  // [END delete_css_product_input]

  public static void main(String[] args) throws Exception {
    final Config config = Config.load();

    // Create a thread pool to delete multiple CSS Products in parallel
    ExecutorService threadPool = Executors.newCachedThreadPool();
    for (int i = 0; i < 100; i++) {
      // The ID uniquely identifying each product. In
      // the format languageCode~countryCode~rawProvidedId
      final String productId = "de~DE~rawProvidedId" + i;
      threadPool.execute(
          () -> {
            try {
              deleteCssProductInput(config, productId);
            } catch (Exception e) {
              System.out.println(e);
            }
          });
    }
  }
}
