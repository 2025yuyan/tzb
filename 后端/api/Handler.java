package api;
import io.vercel.functions.HttpRequest;
import io.vercel.functions.HttpResponse;
import io.vercel.functions.HttpRequestHandler;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.io.PrintWriter;
import java.io.StringWriter;

public class Handler implements HttpRequestHandler {

    // --- 所有静态变量和工具方法从 houduan.java 迁移过来 ---
    private static final String DIAGNOSIS_API_KEY = "6854b6c040ad67027f97f916.zHhzUFvXCOzLNOcM73O7FE7ajU7GqK5w";
    private static final String AI_ASK_API_KEY = "6854b6c040ad67027f97f916.zHhzUFvXCOzLNOcM73O7FE7ajU7GqK5w";
    private static final Set<String> tokens = new HashSet<>();
    private static final List<Map<String, Object>> medicineRemindList = new ArrayList<>();
    private static final List<Map<String, Object>> discussList = new ArrayList<>();
    private static final List<Map<String, Object>> newsList = new ArrayList<>();
    private static final Map<String, Object> user = new HashMap<>();

    static {
        user.put("id", 1); user.put("name", "李大爷"); user.put("phone", "138****8888");
        newsList.add(new HashMap<>() {{ put("id", 1); put("title", "如何健康饮食"); put("content", "..."); }});
        discussList.add(new HashMap<>() {{ put("id", 1); put("user", "张阿姨"); put("content", "最近睡眠质量不太好..."); }});
        medicineRemindList.add(new HashMap<>() {{ put("id", 1); put("time", "08:00"); put("name", "降压药"); put("status", "待服用"); }});
    }
    
    // --- Vercel 的主入口方法 ---
    @Override
    public void handle(HttpRequest request, HttpResponse response) throws IOException {
        try {
            String path = request.getUri().getPath();
            String method = request.getMethod();

            // 全局CORS处理
            response.getHeaders().put("Access-Control-Allow-Origin", "*");
            response.getHeaders().put("Access-control-allow-methods", "GET, POST, OPTIONS, DELETE");
            response.getHeaders().put("Access-Control-Allow-Headers", "Content-Type, Authorization");

            if ("OPTIONS".equalsIgnoreCase(method)) {
                response.setStatusCode(204);
                return;
            }

            // --- 路由分发 ---
            switch (path) {
                case "/api/auth/send-code":
                    handleSendCode(request, response);
                    break;
                case "/api/auth/login-phone":
                case "/api/auth/login-account":
                    handleLogin(request, response);
                    break;
                case "/api/auth/guest-login":
                    handleGuestLogin(request, response);
                    break;
                case "/api/user/profile":
                    handleUserProfile(request, response);
                    break;
                case "/api/health/home":
                    handleHealthHome(request, response);
                    break;
                case "/api/health/records":
                    handleHealthRecords(request, response);
                    break;
                case "/api/ai/diagnosis":
                    handleDiagnosis(request, response);
                    break;
                case "/api/ai/ask":
                    handleAsk(request, response);
                    break;
                case "/api/medicine/remind":
                    handleMedicineRemind(request, response);
                    break;
                case "/api/community/news":
                    handleCommunityNews(request, response);
                    break;
                case "/api/community/discuss":
                    handleCommunityDiscuss(request, response);
                    break;
                case "/api/auth/forgot-password":
                    handleForgotPassword(request, response);
                    break;
                default:
                    sendJsonResponse(response, 404, "{\"code\": 404, \"msg\": \"Not Found\"}");
                    break;
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.getHeaders().put("Content-Type", "application/json; charset=UTF-8");
            
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            String exceptionAsString = sw.toString();
            
            String errorMessage = e.getMessage() != null ? e.getMessage() : "An unexpected error occurred.";

            String jsonError = "{\"code\":500, \"msg\":\"" + jsonEscape(errorMessage) + "\", \"stacktrace\":\"" + jsonEscape(exceptionAsString) + "\"}";
            response.getBody().write(jsonError.getBytes(StandardCharsets.UTF_8));
        }
    }

    // --- 各个接口的具体实现 ---

    private void handleSendCode(HttpRequest request, HttpResponse response) throws IOException {
        sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"验证码已发送\"}");
    }

    private void handleLogin(HttpRequest request, HttpResponse response) throws IOException {
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"登录成功\",\"token\":\""+token+"\",\"user\":{\"id\":1,\"name\":\"李大爷\"}}");
    }

    private void handleGuestLogin(HttpRequest request, HttpResponse response) throws IOException {
        String token = UUID.randomUUID().toString();
        tokens.add(token);
        sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"登录成功\",\"token\":\""+token+"\",\"user\":{\"id\":0,\"name\":\"体验用户\"}}");
    }

    private void handleUserProfile(HttpRequest request, HttpResponse response) throws IOException {
        sendJsonResponse(response, 200, "{\"code\":0,\"user\":{\"id\":1,\"name\":\"李大爷\",\"phone\":\"138****8888\"}}");
    }
    
    private void handleHealthHome(HttpRequest request, HttpResponse response) throws IOException {
        sendJsonResponse(response, 200, "{\"code\":0,\"data\":{\"heartRate\":72,\"bloodPressure\":\"120/80\",\"sleep\":7.5,\"sleepQuality\":15,\"medicineRemind\":[{\"time\":\"08:00\",\"name\":\"降压药\",\"status\":\"待服用\"},{\"time\":\"12:00\",\"name\":\"糖尿病药\",\"status\":\"已服用\"}],\"trend\":{\"dates\":[\"05-20\",\"05-21\",\"05-22\"],\"heartRate\":[74,73,72]}}}");
    }

    private void handleHealthRecords(HttpRequest request, HttpResponse response) throws IOException {
        sendJsonResponse(response, 200, "{\"code\":0,\"records\":[{\"type\":\"history\",\"title\":\"健康历史记录\",\"desc\":\"...\"},{\"type\":\"report\",\"title\":\"医院检查报告\",\"desc\":\"...\"},{\"type\":\"doctor\",\"title\":\"医生诊疗记录\",\"desc\":\"...\"}]}");
    }

    private void handleDiagnosis(HttpRequest request, HttpResponse response) throws IOException {
        try {
            String body = new String(request.getBody().readAllBytes(), StandardCharsets.UTF_8);
            String symptoms = extractJsonField(body, "symptoms");
            String apiUrl = "https://jiutian.10086.cn/largemodel/api/v2/completions";
            String json = "{\"model\":\"jiutian-lan\",\"prompt\":\"" + symptoms + "\",\"max_tokens\":100}";

            // 注意：在无服务器环境中，网络请求需要更健壮的库，但这里为了保持一致性，仍然使用HttpURLConnection
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) new java.net.URL(apiUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + DIAGNOSIS_API_KEY);
            conn.setDoOutput(true);
            try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }
            
            InputStream respIs = conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream();
            String aiResponse = new String(respIs.readAllBytes(), StandardCharsets.UTF_8);
            String diagnosis = extractText(aiResponse);

            sendJsonResponse(response, 200, "{\"code\":0,\"diagnosis\":\"" + jsonEscape(diagnosis) + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            sendJsonResponse(response, 500, "{\"code\":500,\"msg\":\"智能诊断服务异常\"}");
        }
    }
    
    private void handleAsk(HttpRequest request, HttpResponse response) throws IOException {
        // AI问答的逻辑与智能诊断类似，此处省略，你可以自行补充
        try {
            String body = new String(request.getBody().readAllBytes(), StandardCharsets.UTF_8);
            String question = extractJsonField(body, "question");
             String apiUrl = "https://jiutian.10086.cn/largemodel/api/v2/completions";
            String json = "{\"model\":\"jiutian-lan\",\"prompt\":\"" + question + "\",\"max_tokens\":100}";
            
            java.net.HttpURLConnection conn = (java.net.HttpURLConnection) new java.net.URL(apiUrl).openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + AI_ASK_API_KEY);
            conn.setDoOutput(true);
             try (OutputStream os = conn.getOutputStream()) {
                os.write(json.getBytes(StandardCharsets.UTF_8));
            }

            InputStream respIs = conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream();
            String aiResponse = new String(respIs.readAllBytes(), StandardCharsets.UTF_8);
            String answer = extractText(aiResponse);
            
            sendJsonResponse(response, 200, "{\"code\":0,\"answer\":\"" + jsonEscape(answer) + "\"}");
        } catch (Exception e) {
            e.printStackTrace();
            sendJsonResponse(response, 500, "{\"code\":500,\"msg\":\"AI服务异常\"}");
        }
    }
    
    private void handleMedicineRemind(HttpRequest request, HttpResponse response) throws IOException {
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            sendJsonResponse(response, 200, "{\"code\":0,\"remindList\":" + listToJson(medicineRemindList) + "}");
        } else if ("POST".equalsIgnoreCase(method)) {
            sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"保存成功\"}");
        } else if ("DELETE".equalsIgnoreCase(method)) {
             sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"删除成功\"}");
        } else {
            response.setStatusCode(405);
        }
    }

    private void handleCommunityNews(HttpRequest request, HttpResponse response) throws IOException {
        sendJsonResponse(response, 200, "{\"code\":0,\"news\":" + listToJson(newsList) + "}");
    }
    
    private void handleCommunityDiscuss(HttpRequest request, HttpResponse response) throws IOException {
        String method = request.getMethod();
        if ("GET".equalsIgnoreCase(method)) {
            sendJsonResponse(response, 200, "{\"code\":0,\"discuss\":" + listToJson(discussList) + "}");
        } else if ("POST".equalsIgnoreCase(method)) {
            sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"发布成功\"}");
        } else {
             response.setStatusCode(405);
        }
    }
    
    private void handleForgotPassword(HttpRequest request, HttpResponse response) throws IOException {
         sendJsonResponse(response, 200, "{\"code\":0,\"msg\":\"新密码已发送到手机\"}");
    }
    

    // --- 统一的JSON响应方法 ---
    private void sendJsonResponse(HttpResponse response, int statusCode, String json) throws IOException {
        response.setStatusCode(statusCode);
        response.getHeaders().put("Content-Type", "application/json; charset=UTF-8");
        response.getBody().write(json.getBytes(StandardCharsets.UTF_8));
    }

    // --- 你原来的所有工具方法 ---
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
    private static String jsonEscape(String s) {
        if (s == null) return "";
        return s.replace("\\", "\\\\").replace("\"", "\\\"").replace("\n", "\\n").replace("\r", "\\r");
    }
}
