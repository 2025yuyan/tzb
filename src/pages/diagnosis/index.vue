<template>
  <view class="diagnosis-bg">
    <view class="diagnosis-card">
      <text class="diagnosis-label">请描述您的症状：</text>
      <text class="diagnosis-tip">可手写输入或语音描述您的身体状况</text>
      <textarea v-model="symptoms" class="diagnosis-input" placeholder="如：头晕、咳嗽、血压高..." rows="4" />
      <view class="diagnosis-actions">
        <button class="voice-btn" @click="voiceInput">
          <text class="fas fa-microphone"></text> 语音输入
        </button>
        <button class="img-btn" @click="chooseImage">
          <text class="fas fa-image"></text> 添加图片
        </button>
      </view>
      <view v-if="imageUrl" class="img-preview" @click="previewImage">
        <image :src="imageUrl" mode="aspectFit" class="img-thumb" />
      </view>
      <button class="diagnosis-btn" @click="submitDiagnosis" :disabled="loading">{{ loading ? '诊断中...' : '提交诊断' }}</button>
      <view v-if="result" class="diagnosis-result">
        <text class="result-title">诊断建议：</text>
        <text class="result-content">{{ result }}</text>
      </view>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue';
import { onShow } from '@dcloudio/uni-app';
import request from '../../utils/request.js';

const symptoms = ref('');
const result = ref('');
const loading = ref(false);
const imageUrl = ref('');

function voiceInput() {
  uni.showToast({ title: '语音输入功能待接入', icon: 'none' });
}

function chooseImage() {
  uni.chooseImage({
    count: 1,
    sizeType: ['compressed'],
    sourceType: ['album', 'camera'],
    success: res => {
      imageUrl.value = res.tempFilePaths[0];
    }
  });
}

function previewImage() {
  uni.previewImage({
    urls: [imageUrl.value]
  });
}

function submitDiagnosis() {
  if (!symptoms.value.trim() && !imageUrl.value) {
    uni.showToast({ title: '请填写症状或添加图片', icon: 'none' });
    return;
  }
  loading.value = true;
  const token = uni.getStorageSync('token'); // 假设token已存储
  
  console.log("准备发送诊断请求...");
  request({
    url: '/ai/diagnosis',
    method: 'POST',
    header: {
      'Authorization': 'Bearer ' + token,
      'Content-Type': 'application/json'
    },
    data: {
      symptoms: symptoms.value,
      imageUrl: imageUrl.value
    }
  }).then(data => {
    console.log("请求成功:", data);
    result.value = data.diagnosis || '未获取到诊断建议';
    loading.value = false;
  }).catch(err => {
    console.error("请求失败:", err);
    uni.showToast({ title: '请求失败', icon: 'none' });
    loading.value = false;
  });
}
</script>

<style scoped>
.diagnosis-bg {
  min-height: 100vh;
  background: linear-gradient(135deg, #6a8dff 0%, #7ec6f7 100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 0;
}
.diagnosis-card {
  width: 92vw;
  max-width: 420px;
  background: #fff;
  border-radius: 24px;
  box-shadow: 0 4px 32px rgba(0,0,0,0.08);
  padding: 32px 20px 24px 20px;
  margin-top: 32px;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.diagnosis-label {
  font-size: 20px;
  color: #333;
  margin-bottom: 8px;
  font-weight: 500;
}
.diagnosis-tip {
  font-size: 16px;
  color: #888;
  margin-bottom: 12px;
}
.diagnosis-input {
  width: 93%;
  min-height: 90px;
  font-size: 20px;
  border-radius: 12px;
  border: none;
  background: #f5f7fa;
  padding: 16px 14px;
  margin-bottom: 18px;
  resize: none;
}
.diagnosis-actions {
  display: flex;
  gap: 18px;
  margin-bottom: 24px;
}
.voice-btn, .img-btn {
  flex: 1;
  background: #e0eaff;
  color: #6a8dff;
  border: none;
  border-radius: 18px;
  font-size: 22px;
  font-weight: bold;
  height: 64px;
  display: flex;
  align-items: center;
  justify-content: center;
  gap: 10px;
  box-shadow: 0 2px 8px rgba(106,141,255,0.06);
}
.diagnosis-btn {
  width: 90%;
  max-width: 380px;
  margin: 0 auto 18px auto;
  display: block;
  background: linear-gradient(90deg, #6a8dff 0%, #7ec6f7 100%);
  color: #fff;
  border: none;
  border-radius: 22px;
  font-size: 24px;
  font-weight: bold;
  padding: 10px 0;
  box-shadow: 0 4px 16px rgba(106,141,255,0.10);
}
.diagnosis-btn:disabled {
  background: #b3c6ff;
}
.diagnosis-img-block {
  display: flex;
  flex-direction: column;
  align-items: flex-start;
  margin-bottom: 18px;
}
.img-preview {
  margin-bottom: 10px;
}
.img-thumb {
  width: 120px;
  height: 120px;
  border-radius: 12px;
  object-fit: cover;
  border: 2px solid #e0eaff;
}
.diagnosis-result {
  background: #f5f7fa;
  border-radius: 14px;
  padding: 18px 14px;
  margin-top: 8px;
}
.result-title {
  font-size: 18px;
  font-weight: bold;
  color: #6a8dff;
  margin-bottom: 8px;
  display: block;
}
.result-content {
  font-size: 18px;
  color: #333;
  line-height: 1.7;
}
</style> 