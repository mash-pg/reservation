# 予約システム API 設計書

## 1. 概要

会議室予約システムの REST API 設計書。

### ベース URL

```
http://localhost:8080/api
```

### 共通レスポンス形式

**成功時:**
```json
{
  "id": 1,
  "resourceId": 1,
  "startTime": "2026-02-10T10:00:00",
  "endTime": "2026-02-10T11:00:00",
  "status": "PENDING"
}
```

**エラー時:**
```json
{
  "error": "VALIDATION_ERROR",
  "message": "開始時刻は終了時刻より前である必要があります",
  "timestamp": "2026-02-10T20:30:00"
}
```

---

## 2. Resource API（リソース管理）

### 2.1 リソース一覧取得

| 項目 | 内容 |
|------|------|
| メソッド | GET |
| パス | /api/resources |
| 認証 | 不要（Phase 11 で追加予定） |

**レスポンス（200 OK）:**
```json
[
  {
    "id": 1,
    "name": "会議室A",
    "capacity": 10
  },
  {
    "id": 2,
    "name": "会議室B",
    "capacity": 6
  }
]
```

### 2.2 リソース作成

| 項目 | 内容 |
|------|------|
| メソッド | POST |
| パス | /api/resources |
| 認証 | 不要（Phase 11 で追加予定） |

**リクエスト:**
```json
{
  "name": "会議室C",
  "capacity": 8
}
```

**レスポンス（201 Created）:**
```json
{
  "id": 3,
  "name": "会議室C",
  "capacity": 8
}
```

**エラー（400 Bad Request）:**
- name が空
- capacity が 0 以下

---

## 3. Reservation API（予約管理）

### 3.1 予約一覧取得

| 項目 | 内容 |
|------|------|
| メソッド | GET |
| パス | /api/reservations |
| 認証 | 不要（Phase 11 で追加予定） |

**レスポンス（200 OK）:**
```json
[
  {
    "id": 1,
    "resourceId": 1,
    "resourceName": "会議室A",
    "userId": 1,
    "startTime": "2026-02-10T10:00:00",
    "endTime": "2026-02-10T11:00:00",
    "status": "CONFIRMED"
  }
]
```

### 3.2 予約作成

| 項目 | 内容 |
|------|------|
| メソッド | POST |
| パス | /api/reservations |
| 認証 | 不要（Phase 11 で追加予定） |

**リクエスト:**
```json
{
  "resourceId": 1,
  "userId": 1,
  "startTime": "2026-02-10T14:00:00",
  "endTime": "2026-02-10T15:00:00"
}
```

**レスポンス（201 Created）:**
```json
{
  "id": 2,
  "resourceId": 1,
  "resourceName": "会議室A",
  "userId": 1,
  "startTime": "2026-02-10T14:00:00",
  "endTime": "2026-02-10T15:00:00",
  "status": "PENDING"
}
```

**エラー:**
| ステータス | 条件 |
|-----------|------|
| 400 | startTime >= endTime（開始が終了以降） |
| 400 | startTime が過去 |
| 404 | resourceId が存在しない |
| 409 | 同じリソース・時間帯に予約が存在（重複） |

### 3.3 予約確定

| 項目 | 内容 |
|------|------|
| メソッド | PUT |
| パス | /api/reservations/{id}/confirm |
| 認証 | 不要（Phase 11 で追加予定） |

**状態遷移:** PENDING → CONFIRMED

**レスポンス（200 OK）:**
```json
{
  "id": 1,
  "status": "CONFIRMED",
  ...
}
```

**エラー:**
| ステータス | 条件 |
|-----------|------|
| 404 | 予約が存在しない |
| 409 | 現在の状態が PENDING ではない |

### 3.4 予約キャンセル

| 項目 | 内容 |
|------|------|
| メソッド | PUT |
| パス | /api/reservations/{id}/cancel |
| 認証 | 不要（Phase 11 で追加予定） |

**状態遷移:** PENDING or CONFIRMED → CANCELLED

**ビジネスルール:** 予約日の前日まで可能

**レスポンス（200 OK）:**
```json
{
  "id": 1,
  "status": "CANCELLED",
  ...
}
```

**エラー:**
| ステータス | 条件 |
|-----------|------|
| 404 | 予約が存在しない |
| 409 | すでに CANCELLED または COMPLETED |
| 409 | 予約日当日以降（キャンセル期限切れ） |

### 3.5 予約完了

| 項目 | 内容 |
|------|------|
| メソッド | PUT |
| パス | /api/reservations/{id}/complete |
| 認証 | 不要（Phase 11 で追加予定） |

**状態遷移:** CONFIRMED → COMPLETED

**レスポンス（200 OK）:**
```json
{
  "id": 1,
  "status": "COMPLETED",
  ...
}
```

**エラー:**
| ステータス | 条件 |
|-----------|------|
| 404 | 予約が存在しない |
| 409 | 現在の状態が CONFIRMED ではない |

### 3.6 リソース別予約取得

| 項目 | 内容 |
|------|------|
| メソッド | GET |
| パス | /api/reservations/resource/{resourceId} |
| 認証 | 不要（Phase 11 で追加予定） |

**レスポンス（200 OK）:**
```json
[
  {
    "id": 1,
    "resourceId": 1,
    "resourceName": "会議室A",
    "startTime": "2026-02-10T10:00:00",
    "endTime": "2026-02-10T11:00:00",
    "status": "CONFIRMED"
  }
]
```

---

## 4. 状態遷移図

```
    ┌─────────────────────────────────────┐
    │                                     │
    ▼                                     │
PENDING ──confirm()──► CONFIRMED ──complete()──► COMPLETED
    │                      │
    │                      │
    └──────cancel()────────┴──────► CANCELLED
```

| 現在の状態 | 許可されるアクション |
|-----------|---------------------|
| PENDING | confirm, cancel |
| CONFIRMED | complete, cancel |
| COMPLETED | なし（終了状態） |
| CANCELLED | なし（終了状態） |

---

## 5. エラーコード一覧

| エラーコード | HTTP ステータス | 説明 |
|-------------|----------------|------|
| VALIDATION_ERROR | 400 | 入力値が不正 |
| RESOURCE_NOT_FOUND | 404 | リソースが存在しない |
| RESERVATION_NOT_FOUND | 404 | 予約が存在しない |
| DUPLICATE_RESERVATION | 409 | 同じ時間帯に予約が存在 |
| INVALID_STATE_TRANSITION | 409 | 不正な状態遷移 |
| PAST_DATE_RESERVATION | 400 | 過去の日時は予約不可 |
| CANCEL_DEADLINE_PASSED | 409 | キャンセル期限切れ |
