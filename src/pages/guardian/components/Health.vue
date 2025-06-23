<template>
  <section class="page-transition">
    <div class="mb-1 bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-1">
      <p class="max-w-2xl mx-auto" style="text-align: center; color: #6B7280;">小助手正在实时监测并全面分析老人健康数据</p>
    </div>

    <section class="grid grid-cols-1 md:grid-cols-3 gap-1 mb-2">
      <div class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
        <div class="flex justify-center mb-2">
          <div class="w-12 h-12 bg-primary/10 rounded-full flex items-center justify-center">
            <text class="fa-solid fa-heart-pulse text-primary text-xl">心率监测</text>
          </div>
        </div>
        <p class="text-3xl font-bold mb-1">72 <span class="text-lg font-normal">次/分钟</span></p>
        <p class="text-gray-500 text-sm">较昨日 <span class="text-success">↓2</span> 次/分钟</p>
      </div>

      <div class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
        <div class="flex justify-center mb-4">
          <div class="w-12 h-12 bg-secondary/10 rounded-full flex items-center justify-center">
            <text class="fa-solid fa-tint text-secondary text-xl">血压监测</text>
          </div>
        </div>
        <p class="text-3xl font-bold mb-1">120/80 <span class="text-lg font-normal">mmHg</span></p>
        <p class="text-gray-500 text-sm">正常范围内</p>
      </div>

      <div class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
        <div class="flex justify-center mb-4">
          <div class="w-12 h-12 bg-accent/10 rounded-full flex items-center justify-center">
            <text class="fa-solid fa-bed-pulse text-accent text-xl">睡眠质量</text>
          </div>
        </div>
        <p class="text-3xl font-bold mb-1">7.5 <span class="text-lg font-normal">小时</span></p>
        <p class="text-gray-500 text-sm">深度睡眠 <span class="text-success">↑15%</span></p>
      </div>
      <div class="bg-white rounded-xl shadow-sm p-6 card-hover text-center">
        <div class="flex justify-center mb-4">
          <div class="w-12 h-12 bg-accent/10 rounded-full flex items-center justify-center">
            <text class="fas fa-pills text-primary text-xl">服药提醒</text>
          </div>
        </div>
        <div v-if="medicationList.length > 0" class="text-left">
          <div v-for="item in medicationList" :key="item.id">
            <span class="text-lg font-normal">{{ item.time }} - {{ item.name }}</span>
          </div>
        </div>
        <div v-else class="text-gray-500 text-center py-4">
          <span class="text-lg font-normal">今日无服药提醒</span>
        </div>
      </div>
      <div @click="goToDiagnosis" class="bg-white rounded-xl shadow-sm p-6 card-hover cursor-pointer">
        <div class="flex items-center text-primary text-lg font-bold">
          <text class="fa-solid fa-stethoscope"></text>
          <span class="ml-2">智能诊断</span>
        </div>
        <p class="text-sm text-gray-600 mt-2">点击添加图片，让AI帮你读懂体检视图</p>
      </div>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <view class="flex justify-between items-center mb-6">
          <text class="text-xl font-bold">健康趋势</text>
          <view class="flex space-x-2">
              <button class="bg-white hover:bg-gray-100 text-primary border border-primary font-medium py-2 px-4 rounded-lg transition-all duration-300">
                  <text class="fas fa-download mr-1">导出</text> 
              </button>
              <button class="bg-white hover:bg-gray-100 text-primary border border-primary font-medium py-2 px-4 rounded-lg transition-all duration-300">
                  <text class="fas fa-filter mr-1">筛选</text> 
              </button>
          </view>
      </view>
      <view style="height: 300px" class="w-full bg-gray-50 rounded-lg overflow-hidden">
          <v-chart class="chart" :option="chartOption" />
      </view>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">健康建议</h2>
      <div class="space-y-4">
        <div class="bg-gray-50 rounded-lg p-4 flex items-start transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-10 h-10 bg-success/10 rounded-full flex items-center justify-center mr-3 flex-shrink-0">
            <text class="fa-solid fa-check text-success">  心率正常</text>
          </div>
          <div>
            <h3 class="font-medium mb-1"></h3>
            <p class="text-sm text-gray-500">老人心率保持在正常范围内，继续保持当前生活习惯。</p>
          </div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 flex items-start transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-10 h-10 bg-warning/10 rounded-full flex items-center justify-center mr-3 flex-shrink-0">
            <text class="fa-solid fa-exclamation text-warning">  建议增加活动量</text>
          </div>
          <div>
            <h3 class="font-medium mb-1"></h3>
            <p class="text-sm text-gray-500">根据数据分析，建议每天增加30分钟轻度活动，如散步或太极拳。</p>
          </div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 flex items-start transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-10 h-10 bg-primary/10 rounded-full flex items-center justify-center mr-3 flex-shrink-0">
            <text class="fa-solid fa-info text-primary">  定期体检提醒</text>
          </div>
          <div>
            <h3 class="font-medium mb-1"></h3>
            <p class="text-sm text-gray-500">距离上次体检已过去6个月，建议安排一次全面体检。</p>
          </div>
        </div>
      </div>
    </section>
  </section>
</template>

<script setup>
import { ref, onMounted } from 'vue';
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
import { getReminders } from '../../../utils/medicationStore.js';

use([
  CanvasRenderer,
  PieChart,
  LineChart,
  TitleComponent,
  TooltipComponent,
  LegendComponent,
  GridComponent
]);

const medicationList = ref([]);

const goToDiagnosis = () => {
  uni.navigateTo({
    url: '/pages/diagnosis/index'
  });
};

const chartOption = ref({});

onMounted(() => {
  const allReminders = getReminders();
  medicationList.value = allReminders.filter(r => r.enabled);

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
        data: [80, 81, 80, 82, 83, 81, 80]
      }
    ]
  };
});
</script>

<style>
/* Using Tailwind CSS, so most styles are in the template. 
   Adding some scoped styles for robustness. */
.text-success {
    color: #10B981;
}
</style> 