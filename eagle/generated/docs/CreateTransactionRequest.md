

# CreateTransactionRequest


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**amount** | **Double** | Currency amount with up to two decimal places |  |
|**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  |
|**type** | [**TypeEnum**](#TypeEnum) |  |  |
|**reference** | **String** |  |  [optional] |



## Enum: CurrencyEnum

| Name | Value |
|---- | -----|
| GBP | &quot;GBP&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| DEPOSIT | &quot;deposit&quot; |
| WITHDRAWAL | &quot;withdrawal&quot; |



