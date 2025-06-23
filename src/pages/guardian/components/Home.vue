<template>
  <section class="page-transition">
    <div class="mb-1 bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-1">
      <p class="max-w-2xl mx-auto" style="text-align: center; color: #6B7280;">实时监控老人居家活动，提供安全保障</p>
    </div>
    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">实时监控</h2>
      <div class="relative rounded-lg overflow-hidden bg-gray-100 aspect-video mb-4 flex items-center justify-center">
        <div>
            <text class="fa-solid fa-video mr-1">客厅监控</text> 
          </div>
          <div class="text-center text-gray-500">
            <p>&nbsp;</p>
            <text class="fa-solid fa-video-slash text-4xl mb-2 text-center"></text>暂无实时画面
            <p>&nbsp;</p>
        </div>
        <div class="absolute bottom-0 left-0 right-0 bg-black/50 text-white p-3 flex justify-between items-center">
          
          <div class="flex space-x-3">
            <button class="hover:text-gray-300 transition-colors">
              <text class="fa-solid fa-expand"></text>
            </button>
            <button class="hover:text-gray-300 transition-colors">
              <text class="fa-solid fa-record-vinyl text-danger"></text>
            </button>
          </div>
        </div>
      </div>
      <div class="grid grid-cols-2 gap-4">
        <div class="bg-gray-50 rounded-lg p-4 text-center">
          <div class="text-sm text-gray-500 mb-1">当前活动</div>
          <div class="font-medium">看电视</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 text-center">
          <div class="text-sm text-gray-500 mb-1">最后活动时间</div>
          <div class="font-medium">5分钟前</div>
        </div>
      </div>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">历史活动记录</h2>
      <div class="space-y-4">
        <div class="bg-gray-50 rounded-lg p-4 flex justify-between items-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div>
            <div class="font-medium">起床</div>
            <div class="text-sm text-gray-500">今天 06:30</div>
          </div>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full">正常</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 flex justify-between items-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div>
            <div class="font-medium">早餐</div>
            <div class="text-sm text-gray-500">今天 07:15</div>
          </div>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full">正常</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 flex justify-between items-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div>
            <div class="font-medium">服药</div>
            <div class="text-sm text-gray-500">今天 08:00</div>
          </div>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full">已完成</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 flex justify-between items-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div>
            <div class="font-medium">午休</div>
            <div class="text-sm text-gray-500">今天 13:00</div>
          </div>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full">正常</div>
        </div>
      </div>
      <div class="text-center mt-4">
        <button class="bg-white hover:bg-gray-100 text-primary border border-primary font-medium py-2 px-4 rounded-lg transition-all duration-300">查看更多记录</button>
      </div>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">用药管理</h2>
      <button @click="navigateToMedicationReminder" class="bg-primary hover:bg-primary/90 text-white font-bold py-3 px-6 rounded-lg w-full transition-all duration-300">
        服药提醒设置
      </button>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">设备状态</h2>
      <div class="grid grid-cols-2 gap-4">
        <div class="bg-gray-50 rounded-lg p-4 text-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-12 h-12 bg-success/10 rounded-full flex items-center justify-center mx-auto mb-3">
            <text class="fa-solid fa-video text-success text-xl">客厅摄像头</text>
          </div>
          <h3 class="font-medium mb-1"></h3>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full inline-block">在线</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 text-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-12 h-12 bg-success/10 rounded-full flex items-center justify-center mx-auto mb-3">
            <text class="fa-solid fa-bed text-success text-xl">睡眠监测垫</text>
          </div>
          <h3 class="font-medium mb-1"></h3>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full inline-block">在线</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 text-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-12 h-12 bg-success/10 rounded-full flex items-center justify-center mx-auto mb-3">
            <text class="fa-solid fa-heart-pulse text-success text-xl">智能手环</text>
          </div>
          <h3 class="font-medium mb-1"></h3>
          <div class="bg-success/10 text-success text-xs px-2 py-1 rounded-full inline-block">在线</div>
        </div>
        <div class="bg-gray-50 rounded-lg p-4 text-center transition-all duration-300 hover:shadow-lg hover:-translate-y-1">
          <div class="w-12 h-12 bg-warning/10 rounded-full flex items-center justify-center mx-auto mb-3">
            <text class="fa-solid fa-pills text-warning text-xl">智能药盒</text>
          </div>
          <h3 class="font-medium mb-1"></h3>
          <div class="bg-warning/10 text-warning text-xs px-2 py-1 rounded-full inline-block">低电量</div>
        </div>
      </div>
    </section>
  </section>
</template>

<script setup>
function navigateToMedicationReminder() {
  uni.navigateTo({
    url: '/pages/medication/reminder'
  });
}
</script>

<style scoped>
.text-success {
  color: #10B981;
}
.text-warning {
  color: #F59E0B;
}
.text-danger {
  color: #EF4444;
}
</style> 