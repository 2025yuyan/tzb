<template>
  <view class="font-sans bg-light min-h-screen relative">
    <!-- 顶部导航栏 -->
    <view class="bg-white shadow-sm sticky top-0 z-10">
      <view class="container mx-auto px-4 py-3 flex justify-between items-center">
        <view class="flex items-center space-x-2">
          <text class="fas fa-heartbeat text-primary text-xl"></text>
          <text class="text-xl font-bold text-primary">{{ pageTitle }}</text>
        </view>
        <view class="flex items-center space-x-4">
          <button class="text-gray-600 hover:text-primary transition-colors">
            <text class="fas fa-bell text-lg"></text>
          </button>
          <view class="flex items-center space-x-2">
            <image src="" alt="用户头像" class="w-8 h-8 rounded-full object-cover" />
          </view>
        </view>
      </view>
    </view>
    <!-- 页面容器 -->
    <view class="relative pt-4 pb-20 min-h-screen">
      <!-- 健康页面 -->
      <view id="healthPage" class="page" :class="{ active: activePage === 'health' }">
        <view class="container mx-auto px-4 py-6">
          <!-- 欢迎信息 -->
          <view class="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-4 mb-2 ">
            <text class="text-2xl font-bold text-gray-800 mb-2">早上好，李大爷</text>
            <text class="text-gray-600">今天是2025年5月23日，天气晴朗，气温24℃</text>
          </view>
          <view class="grid grid-cols-1 md:grid-cols-3 gap-3 mb-8 ">
            <view class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
                <view class="flex justify-center mb-2">
                    <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center">
                        <text class="fas fa-heartbeat text-primary text-xl"></text>
                    </view>
                </view>
                <text class="text-lg font-semibold mb-1">心率监测</text>
                <text class="text-3xl font-bold mb-1">72 <text class="text-lg font-normal">次/分钟</text></text>
                <text class="text-gray-500 text-sm">较昨日 <text class="text-success">↓2</text> 次/分钟</text>
            </view>
            <view class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
                <view class="flex justify-center mb-2">
                    <view class="w-12 h-12 bg-secondary/10 rounded-full flex items-center justify-center">
                        <text class="fas fa-tint text-secondary text-xl"></text>
                    </view>
                </view>
                <text class="text-lg font-semibold mb-1">血压监测</text>
                <text class="text-3xl font-bold mb-1">120/80 <text class="text-lg font-normal">mmHg</text></text>
                <text class="text-gray-500 text-sm">正常范围内</text>
            </view>
            <view class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
                <view class="flex justify-center mb-4">
                    <view class="w-12 h-12 bg-accent/10 rounded-full flex items-center justify-center">
                        <text class="fas fa-bed text-accent text-xl"></text>
                    </view>
                </view>
                <text class="text-lg font-semibold mb-1">睡眠质量</text>
                <text class="text-3xl font-bold mb-1">7.5 <text class="text-lg font-normal">小时</text></text>
                <text class="text-gray-500 text-sm">深度睡眠 <text class="text-success">↑15%</text></text>
            </view>
          </view>
          <view class="bg-white rounded-xl shadow-sm p-6 mb-8">
            <view class="flex justify-between items-center mb-6">
                <text class="text-xl font-bold">健康趋势</text>
                <view class="flex space-x-2">
                    <button class="btn-secondary">
                        <text class="fas fa-download mr-1"></text> 导出
                    </button>
                    <button class="btn-secondary">
                        <text class="fas fa-filter mr-1"></text> 筛选
                    </button>
                </view>
            </view>
            <view class="h-[300px] w-full bg-gray-50 rounded-lg overflow-hidden">
                <v-chart class="chart" :option="chartOption" />
            </view>
          </view>
          <view class="bg-white rounded-xl shadow p-6 mb-8">
            <text class="text-xl font-bold mb-4">今日服药</text>
            <ul class="text-gray-700 space-y-3">
                <li class="flex justify-between items-center">
                    <view>
                        <text class="text-xl font-medium">08:00</text> - <text class="text-xl">降压药 1 片</text>
                    </view>
                    <text class="text-sm text-warning">30分钟后</text>
                </li>
                <li class="flex justify-between items-center">
                    <view>
                        <text class="text-xl font-medium ">12:00</text> - <text class="text-xl"> 糖尿病药 1 粒   </text>
                    </view>
                    <text class="text-sm text-gray-500">正常</text>
                </li>
                <li class="flex justify-between items-center">
                    <view>
                        <text class="text-xl font-medium">20:00</text> - <text class="text-xl">睡前助眠药 1 粒</text>
                    </view>
                    <text class="text-sm text-gray-500">未服用</text>
                </li>
            </ul>
          <view class="bg-warning/10 rounded-lg p-4 flex items-start mt-6">
            <text class="fas fa-pills text-warning text-xl mr-3 mt-1"></text>
            <view>
              <text class="font-medium">服药提醒——</text>
              <text class="text-sm text-gray-600">降压药建议早晨起床后服用，服用后建议静坐10分钟</text>
            </view>
          </view>
          </view>
          
          <!-- 健康档案 -->
          <view class="bg-white rounded-xl shadow p-6">
            <text class="text-xl font-bold mb-4">健康档案</text>
            <view class="space-y-4">
              <view class="bg-gray-50 rounded-lg p-4 flex items-center justify-between card-hover">
                <view class="flex items-center">
                  <text class="fas fa-file-medical text-primary mr-3 w-6 text-center"></text>
                  <text class="text-xl">健康历史记录</text>
                </view>
                <text class="fas fa-chevron-right text-gray-400"></text>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-center justify-between card-hover">
                <view class="flex items-center">
                  <text class="fas fa-hospital text-primary mr-3 w-6 text-center"></text>
                  <text class="text-xl">医院检查报告</text>
                </view>
                <text class="fas fa-chevron-right text-gray-400"></text>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-center justify-between card-hover">
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
      <view id="aiPage" class="page" :class="{ active: activePage === 'ai' }">
        <view class="container mx-auto px-4 py-6">
          <!-- AI管家介绍 -->
          <view class="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-4 mb-2 text-center">
            <text class="text-2xl font-bold text-gray-800 mb-2">AI健康管家</text>
            <text class="text-gray-600">我是您的专属健康管家，随时为您提供健康咨询和帮助</text>
          </view>

          <!-- 语音对话区域 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">开始对话</text>
            <view class="space-y-4 mb-6">
              <view class="flex items-start">
                <image src="" alt="AI小管家" class="w-8 h-8 rounded-full object-cover mr-3" />
                <view class="chat-bubble chat-ai">
                  <view class="font-medium">小管家</view>
                  <view class="text-sm">您好！今天感觉怎么样？需要我帮您查询健康信息吗？</view>
                </view>
              </view>
              <view class="flex items-start justify-end">
                <view class="chat-bubble chat-user">
                  <view class="font-medium">我</view>
                  <view class="text-sm">我的血压正常吗？</view>
                </view>
              </view>
              <view class="flex items-start">
                <image src="" alt="AI小管家" class="w-8 h-8 rounded-full object-cover mr-3" />
                <view class="chat-bubble chat-ai">
                  <view class="font-medium">小管家</view>
                  <view class="text-sm">您的血压最近一次测量是120/80 mmHg，处于正常范围内，请继续保持健康的生活方式。</view>
                </view>
              </view>
            </view>
            
            <view class="flex space-x-3">
              <input type="text" placeholder="手写出您的问题..." class="flex-1 border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-primary/50" />
              <button class="btn-primary flex items-center justify-center">
                <text class="fas fa-microphone mr-2"></text> 语音输入
              </button>
            </view>
          </view>

          <!-- 常用功能 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">常用功能</text>
            <view class="grid grid-cols-2 gap-4">
              <button class="bg-gray-50 rounded-lg p-4 flex flex-col items-center card-hover">
                <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center mb-2">
                  <text class="fas fa-stethoscope text-primary text-xl"></text>
                </view>
                <text>智能诊断</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-4 flex flex-col items-center card-hover">
                <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center mb-2">
                  <text class="fas fa-pills text-primary text-xl"></text>
                </view>
                <text>服药提醒</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-4 flex flex-col items-center card-hover">
                <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center mb-2">
                  <text class="fas fa-calendar text-primary text-xl"></text>
                </view>
                <text>预约挂号</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-4 flex flex-col items-center card-hover">
                <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center mb-2">
                  <text class="fas fa-phone text-primary text-xl"></text>
                </view>
                <text>紧急联系</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-4 flex flex-col items-center card-hover">
                <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center mb-2">
                  <text class="fas fa-comment-medical text-primary text-xl"></text>
                </view>
                <text>健康咨询</text>
              </button>
              <button class="bg-gray-50 rounded-lg p-4 flex flex-col items-center card-hover">
                <view class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center mb-2">
                  <text class="fas fa-file-medical text-primary text-xl"></text>
                </view>
                <text>档案查询</text>
              </button>
            </view>
          </view>
        </view>
      </view>

      <!-- 社区页面 -->
      <view id="communityPage" class="page" :class="{ active: activePage === 'community' }">
        <view class="container mx-auto px-4 py-6">
          <!-- 社区广场介绍 -->
          <view class="bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-4 mb-2 text-center">
            <text class="text-2xl font-bold text-gray-800 mb-2">社区广场</text>
            <text class="text-gray-600">发现身边的美好，分享身边的快乐</text>
          </view>

          <!-- 热门资讯 -->
          <view class="bg-white rounded-xl shadow-sm p-6 mb-6 card-shadow">
            <text class="text-xl font-bold mb-4">热门资讯</text>
            <view class="space-y-4">
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <image src="" alt="社区资讯" class="w-16 h-16 rounded-lg object-cover mr-3" />
                <view>
                  <view class="font-medium">今日头条：如何健康饮食</view>
                  <text class="text-sm text-gray-600 line-clamp-2">老年人健康饮食的几个要点，帮助您保持身体健康，延缓衰老...</text>
                  <view class="flex items-center mt-1">
                    <text class="text-xs text-gray-400">2小时前</text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-eye mr-1"></text> 128</text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-comment mr-1"></text> 8</text>
                  </view>
                </view>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <image src="" alt="社区资讯" class="w-16 h-16 rounded-lg object-cover mr-3" />
                <view>
                  <view class="font-medium">推荐视频：防跌倒的小妙招</view>
                  <text class="text-sm text-gray-600 line-clamp-2">老年人居家防跌倒的实用技巧，简单易学，有效降低意外风险...</text>
                  <view class="flex items-center mt-1">
                    <text class="text-xs text-gray-400">昨天</text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-eye mr-1"></text> 235</text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-comment mr-1"></text> 15</text>
                  </view>
                </view>
              </view>
              <view class="bg-gray-50 rounded-lg p-4 flex items-start">
                <image src="" alt="社区资讯" class="w-16 h-16 rounded-lg object-cover mr-3" />
                <view>
                  <view class="font-medium">社区活动：下周健康讲座</view>
                  <text class="text-sm text-gray-600 line-clamp-2">社区将于下周三举办健康讲座，邀请专家讲解老年人心血管健康管理知识...</text>
                  <view class="flex items-center mt-1">
                    <text class="text-xs text-gray-400">3天前</text>
                    <text class="text-xs text-gray-400 ml-3"><text class="fas fa-eye mr-1"></text> 186</text>
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
              </view>.
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
                <image src="" alt="用户头像" class="w-10 h-10 rounded-full object-cover mr-3" />
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
                <image src="6" alt="用户头像" class="w-10 h-10 rounded-full object-cover mr-3" />
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
                <image src="" alt="用户头像" class="w-10 h-10 rounded-full object-cover mr-3" />
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

    <!-- 底部导航栏 -->
    <view class="fixed bottom-0 left-0 right-0 bg-white shadow-[0_-2px_10px_rgba(0,0,0,0.05)] z-40">
      <view class="grid grid-cols-3 h-16">
        <view @click="switchPage('health')" class="nav-item" :class="{ active: activePage === 'health' }">
          <text class="fas fa-heartbeat text-xl mb-1"></text>
          <text class="text-xs">健康</text>
        </view>
        <view @click="switchPage('ai')" class="nav-item" :class="{ active: activePage === 'ai' }">
          <text class="fas fa-robot text-xl mb-1"></text>
          <text class="text-xs">AI管家</text>
        </view>
        <view @click="switchPage('community')" class="nav-item" :class="{ active: activePage === 'community' }">
          <text class="fas fa-users text-xl mb-1"></text>
          <text class="text-xs">社区</text>
        </view>
      </view>
    </view>

    <!-- AI小管家悬浮按钮 -->
    <view class="floating-bot">
      <view class="bot-bubble" id="botBubble" :style="{ opacity: botBubbleVisible ? 1 : 0 }">
        <view class="flex items-start">
          <view>
            <view class="font-medium">小管家</view>
            <view class="text-sm">长按我可以语音操作噢</view>
          </view>
        </view>
      </view>
      <view class="bot-icon" id="botIcon" @mouseover="showBotBubble" @mouseleave="hideBotBubble">
        <text class="fas fa-microphone"></text>
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
}

function hideBotBubble() {
  botBubbleVisible.value = false;
}

const chartOption = ref({});

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
});

</script>
<style scoped>
.chart {
  height: 300px;
}
</style> 