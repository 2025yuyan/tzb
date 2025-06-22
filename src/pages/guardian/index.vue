<template>
  <view class="guardian-page">
    <!-- 顶部导航栏 -->
    <view class="header">
      <view class="container mx-auto px-4 py-3 flex justify-between items-center">
        <view class="flex items-center space-x-4">
          <button class="text-gray-600 hover:text-primary transition-colors relative">
            <i class="fa-solid fa-bell text-xl"></i>
            <span class="absolute -top-1 -right-1 bg-danger text-white text-xs rounded-full w-5 h-5 flex items-center justify-center"></span>
          </button>
        </view>
      </view>
    </view>

    <!-- 主要内容区域 -->
    <view class="content">
      <view class="container mx-auto px-4 py-6">
        <Health v-if="activePage === 'health'" />
        <Home v-if="activePage === 'home'" />
        <Learning v-if="activePage === 'learning'" />
      </view>
    </view>

    <!-- 底部导航栏 -->
    <view class="footer">
      <view @click="setPage('health')" :class="['nav-item', activePage === 'health' ? 'active' : '']">
        <text class="fa-solid fa-heartbeat nav-icon"></text>
        <text class="nav-text">健康状态</text>
      </view>
      <view @click="setPage('home')" :class="['nav-item', activePage === 'home' ? 'active' : '']">
        <text class="fa-solid fa-home nav-icon"></text>
        <text class="nav-text">居家情况</text>
      </view>
      <view @click="setPage('learning')" :class="['nav-item', activePage === 'learning' ? 'active' : '']">
        <text class="fa-solid fa-book nav-icon"></text>
        <text class="nav-text">学习中心</text>
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue';
import Health from './components/Health.vue';
import Home from './components/Home.vue';
import Learning from './components/Learning.vue';

const activePage = ref('health');

function setPage(page) {
  activePage.value = page;
}
</script>

<style>
@import "https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css";

/* 整体页面布局 */
.guardian-page {
  display: flex;
  flex-direction: column;
  height: 100%;
  overflow: hidden;
  background-color: #F9FAFB; /* bg-gray-50 */
}

/* 页头 */
.header {
  flex-shrink: 0;
  background-color: white;
  box-shadow: 0 1px 3px 0 rgba(0, 0, 0, 0.1), 0 1px 2px 0 rgba(0, 0, 0, 0.06);
}

/* 内容区域 */
.content {
  flex: 1;
  overflow-y: auto;
}

/* 底部导航栏 */
.footer {
  display: flex;
  flex-shrink: 0;
  height: 76px;
  background-color: white;
  box-shadow: 0 -2px 10px rgba(0,0,0,0.05);
}

.nav-icon {
  font-size: 20px;
  margin-bottom: 4px;
}

.nav-text {
  font-size: 12px;
  font-weight: 500;
}

.nav-item {
  flex: 1;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  height: 100%;
  transition: color 0.2s;
  color: #6B7280; /* text-gray-500 */
  box-sizing: border-box;
}

.nav-item.active {
  color: #4F46E5; /* text-primary */
  border-top: 2px solid #4F46E5; /* border-primary */
}

/* Tailwind颜色变量 */
:root {
  --primary: #4F46E5;
  --danger: #EF4444;
}
.text-primary { color: var(--primary); }
.bg-danger { background-color: var(--danger); }
</style> 