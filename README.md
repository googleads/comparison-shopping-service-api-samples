# Comparison Shopping Service API Samples

This repository contains code samples for the Comparison Shopping Service API
(CSS API). The code samples are categorized by platform/language. Each language
directory contains a `README` file with more information on how to run the
samples for that particular language.

This document covers setting up authentication and the common configuration file
used by all the samples. For more information on the API, please refer to the
[CSS API documentation](https://developers.google.com/comparison-shopping-services/api).


## Choose Your Method of Authentication

Before getting started, check the [Getting Started](https://developers.google.com/comparison-shopping-services/api/guides/quickstart)
section of the CSS API documentation.
You may want to use
[service accounts](https://developers.google.com/shopping-content/v2/how-tos/service-accounts)
to simplify the authentication flow. These samples also support using
[Google Application Default Credentials](https://developers.google.com/identity/protocols/application-default-credentials).

> **Note**
> The current CSS API sample code only demonstrates the use of service accounts
> for authentication. Other [OAuth](https://developers.google.com/shopping-content/guides/how-tos/authorizing)
> authentication methods are also supported, but these are not explicitly
> demonstrated in the API samples.

## Setting up Authentication and Sample Configuration

1.  Create the directory `$(HOME)/comparison-shopping-service-api/config` to store the
    configuration.

    If you are unsure of the location of this directory in your particular
    setup, you may run the samples (following the language-specific `README`).
    Errors from the samples that relate to this directory or necessary files not
    existing will provide the full path to the expected directory or files.

2.  Set up your desired authentication method.

    If you are using Google Application Default Credentials:

    *   Follow the directions on the [Google Application Default
        Credentials](https://developers.google.com/identity/protocols/application-default-credentials)
        page.

    If you are using a service account:

    *   Rename the JSON file downloaded during service account creation to
        `service-account.json` in the `config` directory.

    If you are using an OAuth 2.0 client ID:

    *   Download your [OAuth 2.0 client credentials](https://console.developers.google.com/apis/credentials)
        to the file `client-secrets.json` in the `config` directory.

        **Note:** The samples assume that you are using an OAuth 2.0 client ID
        that can use a loopback IP address to retrieve tokens. If you are not or
        are unsure that you are, please visit the
        [OAuth 2.0 for Mobile & Desktop Apps](https://developers.google.com/identity/protocols/OAuth2InstalledApp)
        page and follow the instructions there to create a new OAuth 2.0 client ID
        to use with the samples.

    It is possible to set up multiple authentication methods for testing
    different flows, but it is important to note that the samples will always
    use the first credentials that can be loaded, in the following order:

    1.  [Google Application Default
        Credentials](https://developers.google.com/identity/protocols/application-default-credentials)
    2.  [Service
        accounts](https://developers.google.com/shopping-content/v2/how-tos/service-accounts)
        credentials
    3.  [OAuth 2.0
        client](https://developers.google.com/shopping-content/v2/how-tos/authorizing)
        credentials

3.  Copy the example `account-info.json` file from the repository root to
    `$HOME/comparison-shopping-service-api/config`. Then, modify its contents as
    needed. The file contains a JSON object with the following fields:

    | Field                     | Type   | Description                                    |
    |---------------------------|--------|------------------------------------------------|
    | `groupId`                 | number | The CSS Group ID to run samples against.       |
    | `domainId`                | number | The CSS Domain ID to run samples against.      |
    | `merchantId`              | number | The Merchant Center ID to run samples against. |

    If OAuth 2.0 client credentials are used, the token details will be stored in
    the `stored-token.json` file in the samples configuration directory after
    authorization has been granted. If authentication issues are encountered,
    the file can be removed and the user will be prompted to re-authorize access.

## Try Out the Samples

Once the common sample configuration file has been configured and authentication
credentials have been set up, it is time to build and run any of the included
samples. As previously mentioned, language-specific instructions are available
in the `README`s located in each language subdirectory.
