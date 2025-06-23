<template>
  <view class="font-sans bg-light min-h-screen relative">
    <!-- 顶部导航栏 -->
    <view class="bg-white shadow-sm sticky top-0 z-10">
      <view class="container mx-auto px-4 py-3 flex justify-between items-center">
        <view class="flex items-center space-x-4">
          <button class="text-gray-600 hover:text-primary transition-colors">
            <text class="fas fa-bell text-lg"></text>
          </button>
          <view class="flex items-center space-x-2">
            <!-- 用户头像已删除 -->
          </view>
        </view>
      </view>
    </view>
    <!-- 页面容器 -->
    <view class="relative page-content">
      <!-- 健康页面 -->
      <view v-if="activePage === 'health'" id="healthPage" class="page active">
        <view class="container mx-auto px-4 py-6">
          <!-- 欢迎信息 -->
          <view class="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-4 mb-2 ">
            <text class="text-2xl font-bold text-gray-800 mb-2">早上好，李大爷\n</text>
            <text class="text-gray-600">今天是2025年5月23日，天气晴朗，气温24℃</text>
          </view>
          <view class="grid grid-cols-1 md:grid-cols-3 gap-3 mb-8 ">
            <view class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
                <view class="flex justify-center mb-2">
                    <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center">
                        <text class="fas fa-heartbeat text-primary text-xl">心率监测</text>
                    </view>
                </view>
                <text class="text-lg font-semibold mb-1"></text>
                <text class="text-3xl font-bold mb-1">72 <text class="text-lg font-normal">次/分钟</text></text>
                <text class="text-gray-500 text-sm">较昨日 <text class="text-success">↓2</text> 次/分钟</text>
            </view>
            <view class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
                <view class="flex justify-center mb-2">
                    <view class="w-12 h-12 bg-secondary/10 rounded-full flex items-center justify-center">
                        <text class="fas fa-tint text-secondary text-xl">血压监测</text>
                    </view>
                </view>
                <text class="text-lg font-semibold mb-1"></text>
                <text class="text-3xl font-bold mb-1">120/80 <text class="text-lg font-normal">mmHg</text></text>
                <text class="text-gray-500 text-sm">-正常范围内</text>
            </view>
            <view class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
                <view class="flex justify-center mb-4">
                    <view class="w-12 h-12 bg-accent/10 rounded-full flex items-center justify-center">
                        <text class="fas fa-bed text-accent text-xl">睡眠质量</text>
                    </view>
                </view>
                <text class="text-lg font-semibold mb-1"></text>
                <text class="text-3xl font-bold mb-1">7.5 <text class="text-lg font-normal">小时</text></text>
                <text class="text-gray-500 text-sm">深度睡眠 <text class="text-success">↑15%</text></text>
            </view>
          </view>
          <view class="bg-white rounded-xl shadow-sm p-6 mb-8">
            <view class="flex justify-between items-center mb-6">
                <text class="text-xl font-bold">健康趋势</text>
                <view class="flex space-x-2">
                    <button class="btn-secondary">
                        <text class="fas fa-download mr-1">导出</text> 
                    </button>
                    <button class="btn-secondary">
                        <text class="fas fa-filter mr-1">筛选</text> 
                    </button>
                </view>
            </view>
            <view class="h-[300px] w-full bg-gray-50 rounded-lg overflow-hidden">
                <v-chart class="chart" :option="chartOption" />
            </view>
          </view>
          <view class="bg-white rounded-xl shadow p-6 mb-8">
            <text class="text-xl font-bold mb-4">今日服药</text>
            <view v-if="medicationList.length > 0" class="text-gray-700 space-y-3">
                <view v-for="item in medicationList" :key="item.id" class="flex justify-between items-center">
                    <view>
                        <text class="text-xl font-medium">{{ item.time }}</text> - <text class="text-xl">{{ item.name }}</text>
                    </view>
                    <text :class="['text-sm', item.status === '已服用' ? 'text-success' : 'text-warning']">{{ item.status }}</text>
                </view>
            </view>
            <view v-else class="text-gray-500 text-center py-4">
              今日无服药提醒
            </view>
          <view class="bg-warning/10 rounded-lg p-4 flex items-start mt-6">
            <text class="fas fa-pills text-warning text-xl mr-3 mt-1">服药提醒</text>
            <view>
              <text class="font-medium"></text>
              <text class="text-sm text-gray-600">降压药建议早晨起床后服用，服用后建议静坐10分钟</text>
            </view>
          </view>
          </view>
          
          <!-- 健康档案 -->
          <view class="bg-white rounded-xl shadow p-6">
            <text class="text-xl font-bold mb-4">健康档案</text>
            <view class="space-y-4">
              <view class="bg-gray-50 rounded-lg p-4 flex items-center justify-between card-hover" @click="goToHealthHistory">
                <view class="flex items-center">
                  <text class="fas fa-file-medical text-primary mr-3 w-6 text-center"></text>
                  <text class="text-xl">健康历史记录</text>
                </view>
                <text class="fas fa-chevron-right text-gray-400"></text>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-center justify-between card-hover" @click="goToHospitalReports">
                <view class="flex items-center">
                  <text class="fas fa-hospital text-primary mr-3 w-6 text-center"></text>
                  <text class="text-xl">医院检查报告</text>
                </view>
                <text class="fas fa-chevron-right text-gray-400"></text>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-center justify-between card-hover" @click="goToDoctorRecords">
                <view class="flex items-center">
                  <text class="fas fa-stethoscope text-primary mr-3 w-6 text-center"></text>
                  <text class="text-xl">医生诊疗记录</text>
                </view>
                <text class="fas fa-chevron-right text-gray-400"></text>
              </view>
            </view>
          </view>
        </view>
      </view>

      <!-- AI管家页面 -->
      <view v-if="activePage === 'ai'" id="aiPage" class="page active">
        <view class="container mx-auto px-4 py-6">
          <!-- AI管家介绍 -->
          <view class="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-4 mb-2 text-center">
            
            <text class="text-gray-600">我是您的专属健康管家，随时为您提供健康咨询和帮助</text>
          </view>

          <!-- 语音对话区域 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">AI对话</text>
            <view class="ai-chat-list">
              <view v-for="(msg, idx) in chatList" :key="idx" :class="['ai-chat-bubble', msg.role]">
                <view v-if="msg.role==='ai'" class="font-medium mb-1">小管家</view>
                <text class="chat-content">{{ msg.content }}</text>
              </view>
            </view>
            <view class="ai-chat-actions">
              <input v-model="inputText" type="text" placeholder="手写出您的问题..." class="ai-chat-input" />
              <button class="ai-voice-btn" @click="voiceInput">
                <text class="fas fa-microphone"></text>
              </button>
              <button class="ai-send-btn" @click="sendMsg" :disabled="!inputText.trim()">发送</button>
            </view>
          </view>

          <!-- 常用功能 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-10 card-shadow">
            <text class="text-2xl font-bold mb-6">常用功能</text>
            <view class="grid grid-cols-2 gap-8">
              <button class="bg-gray-50 rounded-lg p-8 flex flex-col items-center card-hover" @click="goDiagnosis">
                <view class="w-20 h-20 bg-primary/10 rounded-full flex items-center justify-center mb-4">
                  <text class="fas fa-stethoscope text-primary text-3xl"></text>
                </view>
                <text class="text-xl">智能诊断</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-8 flex flex-col items-center card-hover" @click="goToMedicationReminder">
                <view class="w-20 h-20 bg-primary/10 rounded-full flex items-center justify-center mb-4">
                  <text class="fas fa-pills text-primary text-3xl"></text>
                </view>
                <text class="text-xl">服药提醒</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-8 flex flex-col items-center card-hover" @click="goToBooking">
                <view class="w-20 h-20 bg-primary/10 rounded-full flex items-center justify-center mb-4">
                  <text class="fas fa-calendar text-primary text-3xl"></text>
                </view>
                <text class="text-xl">预约挂号</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-8 flex flex-col items-center card-hover" @click="goToEmergencyContact">
                <view class="w-20 h-20 bg-primary/10 rounded-full flex items-center justify-center mb-4">
                  <text class="fas fa-phone text-primary text-3xl"></text>
                </view>
                <text class="text-xl">紧急联系</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-8 flex flex-col items-center card-hover" @click="goToConsultation">
                <view class="w-20 h-20 bg-primary/10 rounded-full flex items-center justify-center mb-4">
                  <text class="fas fa-comment-medical text-primary text-3xl"></text>
                </view>
                <text class="text-xl">健康咨询</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-8 flex flex-col items-center card-hover" @click="goToProfile">
                <view class="w-20 h-20 bg-primary/10 rounded-full flex items-center justify-center mb-4">
                  <text class="fas fa-file-medical text-primary text-3xl"></text>
                </view>
                <text class="text-xl">档案查询</text>
              </button>
            </view>
          </view>
        </view>
      </view>

      <!-- 社区页面 -->
      <view v-if="activePage === 'community'" id="communityPage" class="page active">
        <view class="container mx-auto px-4 py-6">
          <!-- 社区广场介绍 -->
          <view class="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-4 mb-2 text-center">
            <text class="text-gray-600">发现身边的美好，分享身边的快乐</text>
          </view>

          <!-- 热门资讯 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">热门资讯</text>
            <view class="space-y-4">
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <view>
                  <view class="font-medium">今日头条：如何健康饮食</view>
                  <text class="text-sm text-gray-600 line-clamp-2">老年人健康饮食的几个要点，帮助您保持身体健康，延缓衰老...</text>
                  <view class="flex items-center mt-1">
                    <text class="text-xs text-gray-400">2小时前 </text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-eye mr-1"></text> 128 </text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-comment mr-1"></text> 8</text>
                  </view>
                </view>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <view>
                  <view class="font-medium">推荐视频：防跌倒的小妙招</view>
                  <text class="text-sm text-gray-600 line-clamp-2">老年人居家防跌倒的实用技巧，简单易学，有效降低意外风险...</text>
                  <view class="flex items-center mt-1">
                    <text class="text-xs text-gray-400">昨天 </text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-eye mr-1"></text> 235 </text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-comment mr-1"></text> 15</text>
                  </view>
                </view>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <view>
                  <view class="font-medium">社区活动：下周健康讲座</view>
                  <text class="text-sm text-gray-600 line-clamp-2">社区将于下周三举办健康讲座，邀请专家讲解老年人心血管健康管理知识...</text>
                  <view class="flex items-center mt-1">
                    <text class="text-xs text-gray-400">3天前 </text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-eye mr-1"></text> 186 </text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-comment mr-1"></text> 22</text>
                  </view>
                </view>
              </view>
            </view>
            <button class="btn-outline w-full mt-4">查看更多资讯</button>
          </view>

          <!-- 社区活动 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">近期活动</text>
            <view class="space-y-4">
              <view class="bg-gray-50 rounded-lg p-4">
                <view class="flex justify-between items-center mb-2">
                  <view class="font-medium">健康讲座：老年人心血管健康管理</view>
                  <text class="bg-success/10 text-success text-xs px-2 py-1 rounded-full">进行中</text>
                </view>
                <view class="flex items-center text-sm text-gray-600 mb-2">
                  <text class="fas fa-calendar mr-2"></text> 2025年5月28日 上午10:00
                </view>
                <view class="flex items-center text-sm text-gray-600 mb-3">
                  <text class="fas fa-map-marker mr-2"></text> 社区活动中心
                </view>
                <button class="btn-outline text-sm">我要参加</button>
              </view>
              <view class="bg-gray-50 rounded-lg p-4">
                <view class="flex justify-between items-center mb-2">
                  <view class="font-medium">老年人文体活动：广场舞比赛</view>
                  <text class="bg-warning/10 text-warning text-xs px-2 py-1 rounded-full">即将开始</text>
                </view>
                <view class="flex items-center text-sm text-gray-600 mb-2">
                  <text class="fas fa-calendar mr-2"></text> 2025年6月5日 下午2:00
                </view>
                <view class="flex items-center text-sm text-gray-600 mb-3">
                  <text class="fas fa-map-marker mr-2"></text> 社区广场
                </view>
                <button class="btn-outline text-sm">我要参加</button>
              </view>
            </view>
          </view>
          
          <!-- 大家都在讨论 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">大家都在讨论</text>
            <view class="space-y-4">
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <view>
                  <view class="flex justify-between items-center mb-1">
                    <view class="font-medium">张阿姨</view>
                    <text class="text-xs text-gray-400">1小时前</text>
                  </view>
                  <text class="text-sm text-gray-700 mb-2">最近睡眠质量不太好，大家有什么改善睡眠的好方法吗？</text>
                  <view class="flex items-center text-xs text-gray-500">
                    <text class="flex items-center mr-4"><text class="fas fa-thumbs-up mr-1"></text> 12</text>
                    <text class="flex items-center"><text class="fas fa-comment mr-1"></text> 5</text>
                  </view>
                </view>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <view>
                  <view class="flex justify-between items-center mb-1">
                    <view class="font-medium">王大爷</view>
                    <text class="text-xs text-gray-400">3小时前</text>
                  </view>
                  <text class="text-sm text-gray-700 mb-2">社区附近新开的药店有优惠活动，有需要的邻居可以去看看~</text>
                  <view class="flex items-center text-xs text-gray-500">
                    <text class="flex items-center mr-4"><text class="fas fa-thumbs-up mr-1"></text> 8</text>
                    <text class="flex items-center"><text class="fas fa-comment mr-1"></text> 3</text>
                  </view>
                </view>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <view>
                  <view class="flex justify-between items-center mb-1">
                    <view class="font-medium">李奶奶</view>
                    <text class="text-xs text-gray-400">昨天</text>
                  </view>
                  <text class="text-sm text-gray-700 mb-2">分享一个适合老年人的简单食谱，营养又好做，大家可以试试~</text>
                  <view class="flex items-center text-xs text-gray-500">
                    <text class="flex items-center mr-4"><text class="fas fa-thumbs-up mr-1"></text> 15</text>
                    <text class="flex items-center"><text class="fas fa-comment mr-1"></text> 7</text>
                  </view>
                </view>
              </view>
            </view>
            <button class="btn-outline w-full mt-4">查看更多讨论</button>
          </view>
        </view>
      </view>
    </view>

    <!-- 悬浮听筒按钮及气泡 -->
    <view class="fab-bot">
      <view v-if="botBubbleVisible" class="fab-bot-bubble">
        <text>对我说话可以找到你想要的哟</text>
      </view>
      <button class="fab-bot-btn" @click="showBotBubble">
        <text class="fas fa-microphone"></text>
      </button>
    </view>

    <!-- 移动端底部导航栏 -->
    <view class="mobile-bottom-nav">
      <view class="nav-item" :class="{active: activePage==='health'}" @click="switchPage('health')">
        <text class="fas fa-heartbeat icon"></text>
        <text>健康</text>
      </view>
      <view class="nav-item" :class="{active: activePage==='ai'}" @click="switchPage('ai')">
        <text class="fas fa-robot icon"></text>
        <text>AI管家</text>
      </view>
      <view class="nav-item" :class="{active: activePage==='community'}" @click="switchPage('community')">
        <text class="fas fa-users icon"></text>
        <text>社区</text>
      </view>
    </view>

  </view>
</template>
<script setup>
import { ref, onMounted, computed } from 'vue';
import { use } from 'echarts/core';
import { CanvasRenderer } from 'echarts/renderers';
import { PieChart, LineChart } from 'echarts/charts';
import {
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent,
} from 'echarts/components';
import VChart, { THEME_KEY } from 'vue-echarts';
import request from '../../utils/request.js';
import { getReminders } from '../../utils/medicationStore.js';
import { onShow } from '@dcloudio/uni-app';

use([
  CanvasRenderer,
  PieChart,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
]);

const activePage = ref('health');
const botBubbleVisible = ref(false);
let botBubbleTimer = null;

const pageTitles = {
  health: '健康管家',
  ai: 'AI管家',
  community: '社区广场',
};

const pageTitle = computed(() => pageTitles[activePage.value]);

function switchPage(page) {
  activePage.value = page;
}

function showBotBubble() {
  botBubbleVisible.value = true;
  if (botBubbleTimer) clearTimeout(botBubbleTimer);
  botBubbleTimer = setTimeout(() => {
    botBubbleVisible.value = false;
  }, 3000);
}

const chartOption = ref({});

function goDiagnosis() {
  uni.navigateTo({ url: '/pages/diagnosis/index' });
}

function goToMedicationReminder() {
  uni.navigateTo({ url: '/pages/medication/reminder' });
}

function goToBooking() {
	uni.navigateTo({
		url: '/pages/appointment/booking'
	});
}

function goToEmergencyContact() {
	uni.navigateTo({
		url: '/pages/emergency/contact'
	});
}

function goToConsultation() {
	uni.navigateTo({
		url: '/pages/consultation/chat'
	});
}

function goToProfile() {
	uni.navigateTo({
		url: '/pages/profile/view'
	});
}

function goToHealthHistory() {
	uni.navigateTo({
		url: '/pages/history/health'
	});
}

function goToHospitalReports() {
	uni.navigateTo({
		url: '/pages/reports/hospital'
	});
}

function goToDoctorRecords() {
	uni.navigateTo({
		url: '/pages/records/doctor'
	});
}

const chatList = ref([
  { role: 'ai', content: '您好！今天感觉怎么样？需要我帮您查询健康信息吗？' }
]);
const inputText = ref('');
function voiceInput() {
  uni.showToast({ title: '语音输入功能待接入', icon: 'none' });
}
function sendMsg() {
  if (!inputText.value.trim()) return;
  chatList.value.push({ role: 'user', content: inputText.value });
  const userMsg = inputText.value;
  inputText.value = '';
  // 调用后端AI问答接口
  request({
    url: '/ai/ask',
    method: 'POST',
    header: {
      'Authorization': 'Bearer ' + uni.getStorageSync('token'),
      'Content-Type': 'application/json'
    },
    data: { question: userMsg }
  }).then(data => {
    if (data && data.code === 0) {
      chatList.value.push({ role: 'ai', content: data.answer });
      if (data.audioUrl) playAudio(data.audioUrl);
    } else {
      chatList.value.push({ role: 'ai', content: data.msg || 'AI服务暂不可用' });
    }
  }).catch(() => {
    chatList.value.push({ role: 'ai', content: '网络异常，AI服务暂不可用' });
  });
}

let innerAudio = null;
function playAudio(url) {
  if (innerAudio) {
    innerAudio.stop();
    innerAudio.destroy();
  }
  innerAudio = uni.createInnerAudioContext();
  innerAudio.src = url;
  innerAudio.play();
}

const medicationList = ref([]);

// 模拟服药状态，实际项目中应从后端获取
function getMockStatus(time) {
  // 简单模拟，假设8点前的已服用
  const now = new Date();
  const reminderTime = new Date(now.toDateString() + ' ' + time);
  if (reminderTime < now) {
    return '已服用';
  }
  return '未服用';
}

onShow(() => {
  const allReminders = getReminders();
  medicationList.value = allReminders
    .filter(r => r.enabled)
    .map(r => ({ ...r, status: getMockStatus(r.time) }));

  // 刷新当前页面状态
  setPage(activePage.value);
});

function setPage(page) {
  // ... existing code ...
}

onMounted(() => {
  // Init chart
  chartOption.value = {
    tooltip: {
      trigger: 'axis'
    },
    legend: {
      data: ['心率', '高压', '低压']
    },
    grid: {
      left: '3%',
      right: '4%',
      bottom: '3%',
      containLabel: true
    },
    xAxis: {
      type: 'category',
      boundaryGap: false,
      data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
      type: 'value'
    },
    series: [
      {
        name: '心率',
        type: 'line',
        stack: '总量',
        data: [70, 72, 71, 74, 75, 73, 72]
      },
      {
        name: '高压',
        type: 'line',
        stack: '总量',
        data: [120, 122, 121, 124, 125, 123, 122]
      },
      {
        name: '低压',
        type: 'line',
        stack: '总量',
        data: [80, 82, 81, 84, 85, 83, 82]
      }
    ]
  };

  // 初次进入时自动弹出一次气泡
  showBotBubble();
});

</script>
<style scoped>
.chart {
  height: 300px;
}

.mobile-bottom-nav {
  position: fixed;
  left: 0;
  right: 0;
  bottom: 0;
  height: 72px;
  background: #fff;
  box-shadow: 0 -2px 16px rgba(0,0,0,0.06);
  display: flex;
  justify-content: space-around;
  align-items: center;
  z-index: 100;
}
.mobile-bottom-nav .nav-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  font-size: 18px;
  color: #888;
  transition: color 0.2s;
}
.mobile-bottom-nav .nav-item.active {
  color: #007aff;
  font-weight: bold;
}
.mobile-bottom-nav .nav-item .icon {
  font-size: 32px;
  margin-bottom: 2px;
}

.fab-bot {
  position: fixed;
  left: 50%;
  transform: translateX(-50%);
  bottom: 88px;
  z-index: 110;
  display: flex;
  flex-direction: column;
  align-items: center;
}
.fab-bot-btn {
  width: 72px;
  height: 72px;
  background: #007aff;
  color: #fff;
  border-radius: 50%;
  box-shadow: 0 4px 24px rgba(0,0,0,0.18);
  display: flex;
  align-items: center;
  justify-content: center;
  font-size: 38px;
  cursor: pointer;
  border: none;
  outline: none;
  transition: background 0.2s, transform 0.2s;
}
.fab-bot-btn:active {
  background: #005bb5;
  transform: scale(0.96);
}
.fab-bot-bubble {
  background: #fff;
  color: #333;
  font-size: 18px;
  border-radius: 18px;
  box-shadow: 0 4px 24px rgba(0,0,0,0.12);
  padding: 16px 24px;
  margin-bottom: 12px;
  opacity: 0.98;
  animation: fadeIn 0.3s;
  max-width: 260px;
  text-align: center;
}
@keyframes fadeIn {
  from { opacity: 0; transform: translateY(20px); }
  to { opacity: 0.98; transform: translateY(0); }
}

.page-content {
  padding-bottom: 160px;
}

.ai-chat-list {
  height: 450px; /* 控制聊天记录区域高度 */
  overflow-y: auto;
  border: 0px solid #eee;
  margin-bottom: 18px;
  display: flex;
  flex-direction: column;
  gap: 12px;
}
.ai-chat-bubble {
  background: #f5f7fa;
  border-radius: 16px;
  padding: 16px 14px;
  font-size: 20px;
  color: #333;
  max-width: 90%;
  word-break: break-all;
}
.ai-chat-bubble.user {
  align-self: flex-end;
  background: #e0eaff;
  color: #007aff;
}
.ai-chat-bubble.ai {
  align-self: flex-start;
  background: #f5f7fa;
  color: #333;
}
.ai-chat-actions {
  display: flex;
  gap: 14px;
  align-items: center;
}
.ai-chat-input {
  flex: 2;
  height: 56px;
  font-size: 20px;
  border-radius: 16px;
  border: none;
  background: #f5f7fa;
  padding: 0 16px;
}
.ai-voice-btn {
  flex: 1;
  height: 56px;
  background: #e0eaff;
  color: #6a8dff;
  border: none;
  border-radius: 16px;
  font-size: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 8px;
}
.ai-send-btn {
  flex: 1;
  height: 56px;
  background: linear-gradient(90deg, #6a8dff 0%, #7ec6f7 100%);
  color: #fff;
  border: none;
  border-radius: 16px;
  font-size: 20px;
  font-weight: bold;
}
.ai-send-btn:disabled {
  background: #b3c6ff;
}
</style> 