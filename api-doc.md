# 医影随护AI平台 后端接口文档

## 1. 用户相关

### 1.1 获取验证码
- **POST** `/api/auth/send-code`
- **参数**：`phone` (string)
- **成功返回**：
  ```json
  { "code": 0, "msg": "验证码已发送" }
  ```
- **失败返回**：
  ```json
  { "code": 1, "msg": "参数错误" }
  ```

### 1.2 手机号验证码登录
- **POST** `/api/auth/login-phone`
- **参数**：`phone` (string), `code` (string)
- **成功返回**：
  ```json
  { "code": 0, "msg": "登录成功", "token": "xxx", "user": { "id": 1, "name": "李大爷" } }
  ```
- **失败返回**：
  ```json
  { "code": 1, "msg": "验证码错误" }
  ```

### 1.3 账号密码登录
- **POST** `/api/auth/login-account`
- **参数**：`account` (string), `password` (string)
- **成功返回**：
  ```json
  { "code": 0, "msg": "登录成功", "token": "xxx", "user": { "id": 1, "name": "李大爷" } }
  ```
- **失败返回**：
  ```json
  { "code": 1, "msg": "账号或密码错误" }
  ```

### 1.4 免注册体验
- **POST** `/api/auth/guest-login`
- **参数**：无
- **成功返回**：
  ```json
  { "code": 0, "msg": "登录成功", "token": "xxx", "user": { "id": 0, "name": "体验用户" } }
  ```
- **失败返回**：
  ```json
  { "code": 1, "msg": "系统错误" }
  ```

### 1.5 获取用户信息
- **GET** `/api/user/profile`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  { "code": 0, "user": { "id": 1, "name": "李大爷", "phone": "138****8888" } }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

## 2. 健康数据相关

### 2.1 获取健康首页数据
- **GET** `/api/health/home`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  {
    "code": 0,
    "data": {
      "heartRate": 72,
      "bloodPressure": "120/80",
      "sleep": 7.5,
      "sleepQuality": 15,
      "medicineRemind": [
        { "time": "08:00", "name": "降压药", "status": "待服用" },
        { "time": "12:00", "name": "糖尿病药", "status": "已服用" }
      ],
      "trend": {
        "dates": ["05-20", "05-21", "05-22"],
        "heartRate": [74, 73, 72]
      }
    }
  }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

### 2.2 获取健康档案
- **GET** `/api/health/records`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  {
    "code": 0,
    "records": [
      { "type": "history", "title": "健康历史记录", "desc": "..." },
      { "type": "report", "title": "医院检查报告", "desc": "..." },
      { "type": "doctor", "title": "医生诊疗记录", "desc": "..." }
    ]
  }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

## 3. 智能诊断

### 3.1 获取智能诊断建议
- **POST** `/api/ai/diagnosis`
- **Header**：`Authorization: Bearer <token>`
- **参数**：`symptoms` (string)
- **成功返回**：
  ```json
  { "code": 0, "diagnosis": "根据您的症状，建议多休息，必要时就医。", "audioUrl": "https://xxx.com/audio/123.mp3" }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```
- **说明**：`audioUrl` 字段为诊断内容的语音朗读链接，前端可直接播放。

## 4. 用药提醒

### 4.1 获取用药提醒列表
- **GET** `/api/medicine/remind`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  {
    "code": 0,
    "remindList": [
      { "id": 1, "time": "08:00", "name": "降压药", "status": "待服用" }
    ]
  }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

### 4.2 新增/更新用药提醒
- **POST** `/api/medicine/remind`
- **Header**：`Authorization: Bearer <token>`
- **参数**：`id` (int, 可选), `time` (string), `name` (string), `status` (string, 可选)
- **成功返回**：
  ```json
  { "code": 0, "msg": "保存成功" }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

### 4.3 删除用药提醒
- **DELETE** `/api/medicine/remind?id=1`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  { "code": 0, "msg": "删除成功" }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

## 5. AI管家相关

### 5.1 AI健康问答
- **POST** `/api/ai/ask`
- **Header**：`Authorization: Bearer <token>`
- **参数**：`question` (string)
- **成功返回**：
  ```json
  { "code": 0, "answer": "您的血压正常，请继续保持。", "audioUrl": "https://xxx.com/audio/123.mp3" }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```
- **说明**：`audioUrl` 字段为AI回答的语音朗读链接，前端可直接播放。

### 5.2 朗读AI对话内容
- **POST** `/api/ai/tts`
- **Header**：`Authorization: Bearer <token>`
- **参数**：`text` (string)
- **成功返回**：
  ```json
  { "code": 0, "audioUrl": "https://xxx.com/audio/123.mp3" }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```
- **说明**：前端拿到 `audioUrl` 后自动播放即可。

## 6. 社区相关

### 6.1 获取社区资讯
- **GET** `/api/community/news`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  {
    "code": 0,
    "news": [
      { "id": 1, "title": "如何健康饮食", "content": "..." }
    ]
  }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

### 6.2 获取社区讨论
- **GET** `/api/community/discuss`
- **Header**：`Authorization: Bearer <token>`
- **成功返回**：
  ```json
  {
    "code": 0,
    "discuss": [
      { "id": 1, "user": "张阿姨", "content": "最近睡眠质量不太好..." }
    ]
  }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

### 6.3 发布社区讨论
- **POST** `/api/community/discuss`
- **Header**：`Authorization: Bearer <token>`
- **参数**：`content` (string)
- **成功返回**：
  ```json
  { "code": 0, "msg": "发布成功" }
  ```
- **失败返回**：
  ```json
  { "code": 401, "msg": "未登录或token无效" }
  ```

## 7. 其它

### 7.1 忘记密码
- **POST** `/api/auth/forgot-password`
- **参数**：`phone` (string)
- **成功返回**：
  ```json
  { "code": 0, "msg": "新密码已发送到手机" }
  ```
- **失败返回**：
  ```json
  { "code": 1, "msg": "手机号未注册" }
  ```

## 说明
- 需要登录的接口，必须在请求头加 `Authorization: Bearer <token>`
- 返回值中的 `code=0` 表示成功，非0为失败
- 失败返回的 `msg` 字段为错误提示
- AI健康问答和智能诊断接口返回内容均包含 `audioUrl` 字段，前端可直接播放语音 