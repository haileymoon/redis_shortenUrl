---
title: error docs
---
# Introduction

This document will walk you through the error handling mechanism in the <SwmToken path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" pos="5:4:4" line-data="public enum CouponCommon {">`CouponCommon`</SwmToken> enum class. The purpose of this class is to define a comprehensive set of error codes and messages that can be used throughout the application to handle various error scenarios related to coupon operations.

We will cover:

1. Why error codes are grouped by similarity.
2. How specific error codes are defined for different scenarios.
3. The importance of having detailed error messages for user feedback.

# Grouping error codes by similarity

Grouping error codes by similarity allows for easier management and understanding of the types of errors that can occur. It helps developers quickly identify related errors and ensures consistency in error handling across the application.

The <SwmToken path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" pos="5:4:4" line-data="public enum CouponCommon {">`CouponCommon`</SwmToken> enum class is structured to group error codes based on their context, such as parameter validation, data availability, coupon restrictions, and access limitations.

# Parameter validation errors

<SwmSnippet path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" line="14">

---

These error codes are related to invalid parameters provided by the user. They ensure that the application can promptly inform the user about incorrect or missing input data, allowing for corrective action.

```
	, INVALID_PARAMETER_GAME_CODE(22001, "게임코드를 입력해주세요.", 400)
	, INVALID_PARAMETER_LANGUAGE_CD(22002, "언어코드를 입력해주세요.", 400)
	, INVALID_PARAMETER_PID(22003, "플레이어 ID를 입력해주세요.", 400)
	, INVALID_PARAMETER_COUPON_CODE(22004, "쿠폰코드를 입력해주세요.", 400)
	, INVALID_PARAMETER_REWARD(22005, "아이템 선택 정보가 유효하지 않습니다.", 400)
	, INVALID_PARAMETER_REWARD_SELECTED(22006, "아이템을 선택해주세요.", 400)
```

---

</SwmSnippet>

# Data availability errors

<SwmSnippet path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" line="21">

---

These error codes handle scenarios where expected data is not available. They are crucial for informing users when certain game-related data cannot be retrieved, which might affect their ability to proceed with coupon operations.

```
	, NO_DATA_GAME_META(23001, "진행 중인 쿠폰 이벤트가 존재하지 않습니다.", 400)
	, NO_DATA_GAME_CONTENTS(23002, "게임 컨텐츠 데이터가 존재하지 않습니다.", 400)
	, NO_DATA_GAME_WEBPAGE_INFO(23003, "게임 웹페이지 데이터가 존재하지 않습니다.", 400)
	, NO_DATA_REWARD_INFO(23004, "보상 정보 조회 중 오류가 발생했습니다.", 400)
	, NO_DATA_GAME_WEBPAGE_PID(23005, "플레이어 ID 위지 안내 데이터가 존재하지 않습니다.", 400)
```

---

</SwmSnippet>

# Coupon restriction errors

<SwmSnippet path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" line="27">

---

This group of error codes deals with restrictions and limitations on coupon usage. They provide feedback to users when they attempt to use coupons in ways that are not permitted, such as exceeding usage limits or using expired coupons.

```
	, COUPON_RESTRICT(24001, "쿠폰 번호 입력을 연속 10회 실패하셨습니다. 1시간 후 다시 입력 가능합니다.", 400)
	, COUPON_WRONG(24002, "잘못된 쿠폰 번호입니다. 쿠폰을 다시 확인한 후 입력해 주세요.", 400)
	, COUPON_PERIOD_EXPIRATION(24003, "이미 쿠폰을 사용하였거나, 유효기간이 지난 쿠폰입니다. 쿠폰을 다시 확인한 후 입력해 주세요", 400)
	, COUPON_EXCEED_LIMIT(24004, "해당 쿠폰의 교환 횟수를 초과하였습니다.", 400)
	, COUPON_NON_TARGET_USER(24005, "해당 쿠폰 사용 가능한 대상자가 아닙니다.", 400)
	, COUPON_EXHAUST(24006, "준비된 쿠폰이 모두 소진되었습니다.", 400)
```

---

</SwmSnippet>

# Access limitation errors

<SwmSnippet path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" line="34">

---

These error codes are used to indicate when a coupon cannot be used due to geographical or market restrictions. They help ensure that coupons are used in appropriate contexts and prevent misuse.

```
	, COUPON_NOT_ALLOWED_WORLD(24007, "해당 월드에서 사용이 불가능한 쿠폰입니다.", 400)
	, COUPON_NOT_ALLOWED_REGION(24008, "해당 리전에서 사용이 불가능한 쿠폰입니다.", 400)
	, COUPON_NOT_ALLOWED_COUNTRY(24009, "해당 국가에서 사용이 불가능한 쿠폰입니다.", 400)
	, COUPON_NOT_ALLOWED_MARKET(24010, "해당 마켓에서 사용이 불가능한 쿠폰입니다.", 400)
	, COUPON_NOT_ALLOWED_BUILD(24011, "해당 빌드에서 사용이 불가능한 쿠폰입니다.", 400)
```

---

</SwmSnippet>

# General errors

<SwmSnippet path="/src/main/java/com/redis/toy/api/exception/CouponCommon.java" line="40">

---

This section includes error codes for general issues that might occur during coupon operations, such as item provision failures, JSON parsing errors, and database insertion errors. These codes are essential for diagnosing and resolving unexpected issues in the system.

```
	, COUPON_PROVIDE_FAIL(24012, "아이템 지급 중 오류가 발생했습니다.", 500)
	, COUPON_PROVIDE_SUCCESS(200, "아이템이 지급되었습니다.", 200)

	, JSON_PARSE_ERROR(99993, "데이터 변환 중 오류가 발생했습니다.", 400)
	, NO_DATA(99994, "데이터를 찾을 수 없습니다.", 500)
	, INVALID_ACCESS(99995, "잘못된 접근입니다.", 500)
	, ERROR_DB_INSERT(99996, "데이터 저장 중 오류가 발생했습니다.", 500)
	, UNKNOWN_ERROR(99999, "예기치 못한 오류가 발생했습니다. 잠시 후에 다시 시도해주세요.", 500);
```

---

</SwmSnippet>

By organizing error codes in this manner, the application can provide clear and specific feedback to users, enhancing their experience and aiding in troubleshooting.

<SwmMeta version="3.0.0" repo-id="Z2l0aHViJTNBJTNBcmVkaXNfc2hvcnRlblVybCUzQSUzQWhhaWxleW1vb24=" repo-name="redis_shortenUrl"><sup>Powered by [Swimm](https://app.swimm.io/)</sup></SwmMeta>
