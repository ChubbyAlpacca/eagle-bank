

# TransactionResponse


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**id** | **String** |  |  |
|**amount** | **Double** |  |  |
|**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  |
|**type** | [**TypeEnum**](#TypeEnum) |  |  |
|**reference** | **String** |  |  [optional] |
|**userId** | **String** |  |  [optional] |
|**createdTimestamp** | **OffsetDateTime** |  |  |



## Enum: CurrencyEnum

| Name | Value |
|---- | -----|
| GBP | &quot;GBP&quot; |



## Enum: TypeEnum

| Name | Value |
|---- | -----|
| DEPOSIT | &quot;deposit&quot; |
| WITHDRAWAL | &quot;withdrawal&quot; |



