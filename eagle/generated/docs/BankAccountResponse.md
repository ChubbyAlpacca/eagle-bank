

# BankAccountResponse


## Properties

| Name | Type | Description | Notes |
|------------ | ------------- | ------------- | -------------|
|**accountNumber** | **String** |  |  |
|**sortCode** | [**SortCodeEnum**](#SortCodeEnum) |  |  |
|**name** | **String** |  |  |
|**accountType** | [**AccountTypeEnum**](#AccountTypeEnum) |  |  |
|**balance** | **Double** | Currency amount with up to two decimal places |  |
|**currency** | [**CurrencyEnum**](#CurrencyEnum) |  |  |
|**createdTimestamp** | **OffsetDateTime** |  |  |
|**updatedTimestamp** | **OffsetDateTime** |  |  |



## Enum: SortCodeEnum

| Name | Value |
|---- | -----|
| _10_10_10 | &quot;10-10-10&quot; |



## Enum: AccountTypeEnum

| Name | Value |
|---- | -----|
| PERSONAL | &quot;personal&quot; |



## Enum: CurrencyEnum

| Name | Value |
|---- | -----|
| GBP | &quot;GBP&quot; |



