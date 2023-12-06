# Comparison Shopping Service API Java Samples

This is a simple set of Java samples, which demonstrates a basic example of the
Comparison Shopping Service API (CSS API) integration in a command-line application.

This starter project provides an excellent foundation for exploring the
[CSS API](https://developers.google.com/comparison-shopping-services/api).

## Prerequisites

Please make sure that you're running Java 8+. If you use Maven, you can use
the included `pom.xml` to install the required dependencies with the
`mvn install` command. Otherwise, install the
[CSS API Client Library for Java](https://developers.google.com/comparison-shopping-services/api/client-libraries).

> **Note**
> The CSS API Client Library for Java is currently not available on Maven and
> needs to be installed locally by downloading it from the
> [developer documentation](https://developers.google.com/comparison-shopping-services/api/client-libraries).
> Additionally, the Java Development Kit (JDK) is required to install the CSS
> API Client Library on your local machine. Ensure the JDK has a minimum version
> of Java 8.

## Setup Authentication and Sample Configuration

If you have not already done so, please read the top-level `README` to learn
how to set up both authentication and the common sample configuration. The
remainder of this document assumes that you have performed both of these tasks.

It is important to note that for the non-service account OAuth flow, the application will read and store the created OAuth 2.0 credentials from the file `token.json` located in the directory `$(HOME)/comparison-shopping-service-api/config`.

If your refresh token is [revoked or has expired](https://developers.google.com/identity/protocols/oauth2#expiration), you may attempt to resolve the error by deleting the `token.json` file and then re-running the sample code to create and save a new refresh token.

## Running the Samples

We assume that you have checked out the code and are reading this from a local
directory. If not, check out the code to a local directory and set up the
project appropriately for access to the Google APIs Client Library for Java.

This section assumes that you have already cloned the code and are reading from
a local directory. If this is not the case, please clone the code to a local
directory and set up your project appropriately for access to the Google APIs
Client Library for Java (see the prerequisites section above).

Build and run any of the included samples in your preferred IDE.

To build and run samples using Maven, navigate to the directory containing the
`pom.xml` file. Then, run `mvn compile`.

If the code compiles successfully, then run `mvn exec`, followed by the name of
the sample you wish to execute. The specific syntax is shown in the examples
below.

### Examples
Use the following syntax to run the InsertCssProductInput class.

```
mvn exec:java -Dexec.mainClass="shopping.css.samples.v1.cssproducts.InsertCssProductInput"
```

Use the following syntax to run the DeleteCssProductInput class.

```
mvn exec:java -Dexec.mainClass="shopping.css.samples.v1.cssproducts.DeleteCssProductInput"
```

Examine your shell output, be inspired and start working on an amazing new app!

We hope these samples give you the inspiration needed to create your new
application!
