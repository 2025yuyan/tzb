import com.sun.net.httpserver.*;
import java.io.*;
import java.net.InetSocketAddress;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class houduan {
    // 智能诊断API KEY
    private static final String DIAGNOSIS_API_KEY = "6854b6c040ad67027f97f916.zHhzUFvXCOzLNOcM73O7FE7ajU7GqK5w";
    // 简单token存储
    private static final Set<String> tokens = new HashSet<>();
    // 用药提醒内存数据
    private static final List<Map<String, Object>> medicineRemindList = new ArrayList<>();
    // 社区讨论内存数据
    private static final List<Map<String, Object>> discussList = new ArrayList<>();
    // 资讯内存数据
    private static final List<Map<String, Object>> newsList = new ArrayList<>();
    // 用户信息
    private static final Map<String, Object> user = new HashMap<>();
    // AI健康问答API KEY
    private static final String AI_ASK_API_KEY = "6854b6c040ad67027f97f916.zHhzUFvXCOzLNOcM73O7FE7ajU7GqK5w";
    static {
        user.put("id", 1);
        user.put("name", "李大爷");
        user.put("phone", "138****8888");
        newsList.add(new HashMap<>() {{
            put("id", 1); put("title", "如何健康饮食"); put("content", "...");
        }});
        discussList.add(new HashMap<>() {{
            put("id", 1); put("user", "张阿姨"); put("content", "最近睡眠质量不太好...");
        }});
        medicineRemindList.add(new HashMap<>() {{
            put("id", 1); put("time", "08:00"); put("name", "降压药"); put("status", "待服用");
        }});
    }
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8081), 0);
        // 用户相关
        server.createContext("/api/auth/send-code", exchange -> {
            if (exchange.getRequestMethod().equals("POST")) {
                sendJson(exchange, "{\"code\":0,\"msg\":\"验证码已发送\"}");
            } else methodNotAllowed(exchange);
        });
        server.createContext("/api/auth/login-phone", exchange -> {
            if (exchange.getRequestMethod().equals("POST")) {
                String token = UUID.randomUUID().toString();
                tokens.add(token);
                sendJson(exchange, "{\"code\":0,\"msg\":\"登录成功\",\"token\":\""+token+"\",\"user\":{\"id\":1,\"name\":\"李大爷\"}}");
            } else methodNotAllowed(exchange);
        });
        server.createContext("/api/auth/login-account", exchange -> {
            if (exchange.getRequestMethod().equals("POST")) {
                String token = UUID.randomUUID().toString();
                tokens.add(token);
                sendJson(exchange, "{\"code\":0,\"msg\":\"登录成功\",\"token\":\""+token+"\",\"user\":{\"id\":1,\"name\":\"李大爷\"}}");
            } else methodNotAllowed(exchange);
        });
        server.createContext("/api/auth/guest-login", exchange -> {
            if (exchange.getRequestMethod().equals("POST")) {
                String token = UUID.randomUUID().toString();
                tokens.add(token);
                sendJson(exchange, "{\"code\":0,\"msg\":\"登录成功\",\"token\":\""+token+"\",\"user\":{\"id\":0,\"name\":\"体验用户\"}}");
            } else methodNotAllowed(exchange);
        });
        server.createContext("/api/user/profile", exchange -> {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if (exchange.getRequestMethod().equals("GET")) {
                sendJson(exchange, "{\"code\":0,\"user\":{\"id\":1,\"name\":\"李大爷\",\"phone\":\"138****8888\"}}");
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });
        // 健康数据
        server.createContext("/api/health/home", exchange -> {
            if (exchange.getRequestMethod().equals("GET") && checkToken(exchange)) {
                sendJson(exchange, "{\"code\":0,\"data\":{\"heartRate\":72,\"bloodPressure\":\"120/80\",\"sleep\":7.5,\"sleepQuality\":15,\"medicineRemind\":[{\"time\":\"08:00\",\"name\":\"降压药\",\"status\":\"待服用\"},{\"time\":\"12:00\",\"name\":\"糖尿病药\",\"status\":\"已服用\"}],\"trend\":{\"dates\":[\"05-20\",\"05-21\",\"05-22\"],\"heartRate\":[74,73,72]}}}");
            } else methodNotAllowed(exchange);
        });
        server.createContext("/api/health/records", exchange -> {
            if (exchange.getRequestMethod().equals("GET") && checkToken(exchange)) {
                sendJson(exchange, "{\"code\":0,\"records\":[{\"type\":\"history\",\"title\":\"健康历史记录\",\"desc\":\"...\"},{\"type\":\"report\",\"title\":\"医院检查报告\",\"desc\":\"...\"},{\"type\":\"doctor\",\"title\":\"医生诊疗记录\",\"desc\":\"...\"}]}");
            } else methodNotAllowed(exchange);
        });
        // 智能诊断
        server.createContext("/api/ai/diagnosis", exchange -> {
            System.out.println("收到智能诊断请求: " + exchange.getRequestMethod());
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.getResponseHeaders().add("Access-control-allow-origin", "*");
                try {
                    System.out.println("1. 开始处理诊断请求...");
                    InputStream is = exchange.getRequestBody();
                    String body = new String(is.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
                    String symptoms = extractJsonField(body, "symptoms");
                    System.out.println("2. 获取到症状: " + symptoms);
                    String apiUrl = "https://jiutian.10086.cn/largemodel/api/v2/completions";
                    String json = "{" +
                        "\"model\":\"jiutian-lan\"," +
                        "\"prompt\":\"" + symptoms + "\"," +
                        "\"max_tokens\":100" +
                        "}";
                    System.out.println("3. 准备请求外部AI接口，URL: " + apiUrl);
                    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) new java.net.URL(apiUrl).openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Authorization", "Bearer " + DIAGNOSIS_API_KEY);
                    conn.setDoOutput(true);
                    System.out.println("4. 正在发送请求到AI接口...");
                    try (java.io.OutputStream os = conn.getOutputStream()) {
                        os.write(json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                    }
                    int code = conn.getResponseCode();
                    System.out.println("5. AI接口返回状态码: " + code);
                    java.io.InputStream respIs = (code == 200) ? conn.getInputStream() : conn.getErrorStream();
                    StringBuilder respSb = new StringBuilder();
                    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(respIs, java.nio.charset.StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) respSb.append(line);
                    }
                    String aiResponse = respSb.toString();
                    System.out.println("6. AI接口返回完整内容: " + aiResponse);
                    String diagnosis = extractText(aiResponse);
                    System.out.println("7. 提取到的诊断文本: " + diagnosis);
                    String finalJson = "{\"code\":0,\"diagnosis\":\"" + jsonEscape(diagnosis) + "\"}";
                    System.out.println("8. 准备返回给前端的内容: " + finalJson);
                    sendJson(exchange, finalJson);
                    System.out.println("9. 已成功返回给前端。");
                } catch (Exception e) {
                    System.out.println("!!! 诊断接口处理异常 !!!");
                    e.printStackTrace();
                    sendJson(exchange, "{\"code\":500,\"msg\":\"智能诊断服务异常\"}");
                }
            } else {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(405, -1);
            }
        });
        // 用药提醒
        server.createContext("/api/medicine/remind", exchange -> {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, DELETE, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            String method = exchange.getRequestMethod();
            if (method.equals("GET")) {
                StringBuilder sb = new StringBuilder();
                sb.append("{\"code\":0,\"remindList\":");
                sb.append(listToJson(medicineRemindList));
                sb.append("}");
                sendJson(exchange, sb.toString());
            } else if (method.equals("POST")) {
                sendJson(exchange, "{\"code\":0,\"msg\":\"保存成功\"}");
            } else if (method.equals("DELETE")) {
                sendJson(exchange, "{\"code\":0,\"msg\":\"删除成功\"}");
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });
        // AI健康问答
        server.createContext("/api/ai/ask", exchange -> {
            System.out.println("收到AI对话请求");
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            if (exchange.getRequestMethod().equalsIgnoreCase("POST")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                try {
                    InputStream is = exchange.getRequestBody();
                    String body = new String(is.readAllBytes(), java.nio.charset.StandardCharsets.UTF_8);
                    String question = extractJsonField(body, "question");
                    String apiUrl = "https://jiutian.10086.cn/largemodel/api/v2/completions";
                    String json = "{" +
                        "\"model\":\"jiutian-lan\"," +
                        "\"prompt\":\"" + question + "\"," +
                        "\"max_tokens\":100" +
                        "}";
                    java.net.HttpURLConnection conn = (java.net.HttpURLConnection) new java.net.URL(apiUrl).openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setRequestProperty("Authorization", "Bearer " + AI_ASK_API_KEY);
                    conn.setDoOutput(true);
                    try (java.io.OutputStream os = conn.getOutputStream()) {
                        os.write(json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
                    }
                    int code = conn.getResponseCode();
                    java.io.InputStream respIs = (code == 200) ? conn.getInputStream() : conn.getErrorStream();
                    StringBuilder respSb = new StringBuilder();
                    try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(respIs, java.nio.charset.StandardCharsets.UTF_8))) {
                        String line;
                        while ((line = br.readLine()) != null) respSb.append(line);
                    }
                    System.out.println("AI接口返回：" + respSb.toString());
                    String answer = extractText(respSb.toString());
                    // String audioUrl = callTTS(answer);
                    sendJson(exchange, "{\"code\":0,\"answer\":\"" + jsonEscape(answer) + "\"}");
                } catch (Exception e) {
                    e.printStackTrace();
                    sendJson(exchange, "{\"code\":500,\"msg\":\"AI服务异常\"}");
                }
            } else {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.sendResponseHeaders(405, -1);
            }
        });
        // AI朗读
        server.createContext("/api/ai/tts", exchange -> {
            if (exchange.getRequestMethod().equals("POST") && checkToken(exchange)) {
                sendJson(exchange, "{\"code\":0,\"audioUrl\":\"https://xxx.com/audio/123.mp3\"}");
            } else methodNotAllowed(exchange);
        });
        // 社区资讯
        server.createContext("/api/community/news", exchange -> {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if (exchange.getRequestMethod().equals("GET")) {
                StringBuilder sb = new StringBuilder();
                sb.append("{\"code\":0,\"news\":");
                sb.append(listToJson(newsList));
                sb.append("}");
                sendJson(exchange, sb.toString());
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });
        // 社区讨论列表
        server.createContext("/api/community/discuss", exchange -> {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            String method = exchange.getRequestMethod();
            if (method.equals("GET")) {
                StringBuilder sb = new StringBuilder();
                sb.append("{\"code\":0,\"discuss\":");
                sb.append(listToJson(discussList));
                sb.append("}");
                sendJson(exchange, sb.toString());
            } else if (method.equals("POST")) {
                sendJson(exchange, "{\"code\":0,\"msg\":\"发布成功\"}");
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });
        // 忘记密码
        server.createContext("/api/auth/forgot-password", exchange -> {
            if (exchange.getRequestMethod().equalsIgnoreCase("OPTIONS")) {
                exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
                exchange.getResponseHeaders().add("Access-Control-Allow-Methods", "POST, OPTIONS");
                exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "Content-Type, Authorization");
                exchange.sendResponseHeaders(204, -1);
                return;
            }
            exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
            if (exchange.getRequestMethod().equals("POST")) {
                sendJson(exchange, "{\"code\":0,\"msg\":\"新密码已发送到手机\"}");
            } else {
                exchange.sendResponseHeaders(405, -1);
            }
        });
        server.setExecutor(null);
        System.out.println("服务已启动，端口8080");
        server.start();
    }
    private static void sendJson(HttpExchange exchange, String json) {
        try {
            exchange.getResponseHeaders().set("Content-Type", "application/json; charset=UTF-8");
            byte[] resp = json.getBytes(StandardCharsets.UTF_8);
            exchange.sendResponseHeaders(200, resp.length);
            OutputStream os = exchange.getResponseBody();
            os.write(resp);
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static boolean checkToken(HttpExchange exchange) {
        List<String> auth = exchange.getRequestHeaders().get("Authorization");
        if (auth == null || auth.isEmpty()) return false;
        String token = auth.get(0).replace("Bearer ", "");
        return tokens.contains(token);
    }
    private static void methodNotAllowed(HttpExchange exchange) throws IOException {
        exchange.sendResponseHeaders(405, -1);
    }
    private static String listToJson(List<Map<String, Object>> list) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < list.size(); i++) {
            sb.append(mapToJson(list.get(i)));
            if (i < list.size() - 1) sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
    private static String mapToJson(Map<String, Object> map) {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        int i = 0;
        for (Map.Entry<String, Object> e : map.entrySet()) {
            sb.append("\"").append(e.getKey()).append("\":");
            Object v = e.getValue();
            if (v instanceof String) sb.append("\"").append(v).append("\"");
            else sb.append(v);
            if (i < map.size() - 1) sb.append(",");
            i++;
        }
        sb.append("}");
        return sb.toString();
    }
    // 工具方法：简单提取json字段
    private static String extractJsonField(String json, String key) {
        int idx = json.indexOf("\"" + key + "\"");
        if (idx == -1) return "";
        int c1 = json.indexOf(":", idx);
        if (c1 == -1) return "";
        int q1 = json.indexOf('"', c1 + 1);
        int q2 = json.indexOf('"', q1 + 1);
        if (q1 == -1 || q2 == -1) return "";
        return json.substring(q1 + 1, q2);
    }
    // 工具方法：调用TTS朗读接口
    private static String callTTS(String text) {
        try {
            String apiUrl = "http://localhost:8081/api/ai/tts";
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) new java.net.URL(apiUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);
            String json = "{\"text\":\"" + text.replace("\"", "\\\"") + "\"}";
            try (java.io.OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            }
            int code = conn.getResponseCode();
            java.io.InputStream respIs = (code == 200) ? conn.getInputStream() : conn.getErrorStream();
            StringBuilder respSb = new StringBuilder();
            try (java.io.BufferedReader br = new java.io.BufferedReader(new java.io.InputStreamReader(respIs, java.nio.charset.StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) respSb.append(line);
            }
            // 提取audioUrl字段
            return extractJsonField(respSb.toString(), "audioUrl");
        } catch (Exception e) {
            return "";
        }
    }
    // 新增：提取choices[0].text字段的方法
    private static String extractText(String json) {
        int idx = json.indexOf("\"choices\"");
        if (idx == -1) return "";
        int textIdx = json.indexOf("\"text\"", idx);
        if (textIdx == -1) return "";
        int colon = json.indexOf(':', textIdx);
        if (colon == -1) return "";
        int q1 = json.indexOf('"', colon + 1);
        int q2 = json.indexOf('"', q1 + 1);
        if (q1 == -1 || q2 == -1) return "";
        return json.substring(q1 + 1, q2);
    }
    // 工具方法：对字符串做JSON转义，防止前端解析失败
    private static String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\")
                .replace("\"", "\\\"")
                .replace("\n", "\\n")
                .replace("\r", "\\r");
    }
}
