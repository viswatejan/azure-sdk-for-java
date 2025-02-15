## Release History

### 1.0.0-beta.6 (Unreleased)

#### Features Added

#### Breaking Changes

#### Bugs Fixed

#### Other Changes

### 1.0.0-beta.5 (2023-08-21)

#### Bugs Fixed
* Fixed an issue where connection is being closed when inject `connection_delay` rule with configured delay smaller than configured `connectionTimeout` - See [PR 35852](https://github.com/Azure/azure-sdk-for-java/pull/35852)
* Fixed an issue where `java.lang.NoClassDefFoundError: com/azure/cosmos/implementation/faultinjection/IRntbdServerErrorInjector` is thrown when using `FaultInjection version 1.0.0-beta.4` - See [Issue 36381](https://github.com/Azure/azure-sdk-for-java/issues/36381)

#### Other Changes
* Updated azure-cosmos version to 4.49.0.

### 1.0.0-beta.4 (2023-07-18)

#### Features Added
* Added fault injection support for Gateway connection mode - See [PR 35378](https://github.com/Azure/azure-sdk-for-java/pull/35378)

#### Bugs Fixed
* Fixed an issue where `FaultInjectionServerErrorType.TIMEOUT` is not injecting the correct error response - See [PR 34723](https://github.com/Azure/azure-sdk-for-java/pull/34723)
* Fixed an issue where connection error is not being injected when FaultInjectionEndpoints is not configured - See [PR 35034](https://github.com/Azure/azure-sdk-for-java/pull/35034)

#### Other Changes
* Updated azure-cosmos version to 4.48.0.

### 1.0.0-beta.3 (2023-04-21)

#### Features Added
* Added `getHitCountDetails` in `FaultInjectionRule` - See [PR 34581](https://github.com/Azure/azure-sdk-for-java/pull/34581)

#### Other Changes
* Added `faultInjectionEvaluationResults` in `CosmosDiagnostics` - See [PR 34581](https://github.com/Azure/azure-sdk-for-java/pull/34581)

### 1.0.0-beta.2 (2023-04-06)

#### Bugs Fixed
* Fixed an issue where `CONNECTION_DELAY` fault injection rule is not applied during `openConnectionsAndInitCaches` - See [PR 34096](https://github.com/Azure/azure-sdk-for-java/pull/34096)
* Fix an issue where `hitCount` is not being tracked properly for connection error type rules - See [PR 34295](https://github.com/Azure/azure-sdk-for-java/pull/34295)

### 1.0.0-beta.1 (2023-03-17)

#### Features Added
* Added fault injection support - See [PR 33329](https://github.com/Azure/azure-sdk-for-java/pull/33329) 

