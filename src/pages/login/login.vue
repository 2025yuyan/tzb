<template>
  <view class="login-bg">
    <view class="login-header">
      <text class="login-title">医影随护AI平台</text>
      <text class="login-desc">智能健康管理，贴心医疗陪伴</text>
    </view>
    <view class="login-card">
      <view class="login-tabs">
        <view :class="['login-tab', loginType==='phone' ? 'active' : '']" @click="loginType='phone'">手机号登录</view>
        <view :class="['login-tab', loginType==='account' ? 'active' : '']" @click="loginType='account'">账号密码登录</view>
      </view>
      <view v-if="loginType==='phone'">
        <view class="login-input">
          <text class="fas fa-mobile-alt input-icon"></text>
          <input v-model="phone" type="text" maxlength="11" placeholder="请输入手机号" />
        </view>
        <view class="code-row">
          <view class="login-input flex-1">
            <text class="fas fa-lock input-icon"></text>
            <input v-model="code" type="text" maxlength="6" placeholder="请输入验证码" />
          </view>
          <button class="code-btn" :disabled="codeTimer>0 || !phone" @click="getCode">
            {{ codeTimer>0 ? codeTimer+'s' : '获取验证码' }}
          </button>
        </view>
      </view>
      <view v-else>
        <view class="login-input">
          <text class="fas fa-user input-icon"></text>
          <input v-model="account" type="text" placeholder="请输入账号" />
        </view>
        <view class="login-input">
          <text class="fas fa-lock input-icon"></text>
          <input v-model="password" type="password" placeholder="请输入密码" />
        </view>
      </view>
      <view class="login-btns-row">
        <button class="login-btn" @click="handleLogin">登录</button>
        <button class="guest-btn" @click="guestLogin">免注册体验</button>
      </view>
      <view class="login-links">
        <text class="link" @click="showToast('请联系管理员重置密码')">忘记密码?</text>
        <text class="link" @click="showToast('请联系管理员注册账号')">注册账号</text>
      </view>
      <button class="guardian-btn" @click="guardianLogin">监护人模式</button>
    </view>
  </view>
</template>

<script setup>
import { ref } from 'vue';
import { onLoad } from '@dcloudio/uni-app';

const loginType = ref('phone');
const phone = ref('');
const code = ref('');
const codeTimer = ref(0);
const account = ref('');
const password = ref('');
let timer = null;

function getCode() {
  if (!/^1[3-9]\d{9}$/.test(phone.value)) {
    uni.showToast({ title: '请输入正确手机号', icon: 'none' });
    return;
  }
  codeTimer.value = 60;
  timer = setInterval(() => {
    codeTimer.value--;
    if (codeTimer.value <= 0) clearInterval(timer);
  }, 1000);
  uni.showToast({ title: '验证码已发送(模拟:123456)', icon: 'none' });
}

function handleLogin() {
  if (loginType.value === 'phone') {
    if (!/^1[3-9]\d{9}$/.test(phone.value)) {
      uni.showToast({ title: '请输入正确手机号', icon: 'none' });
      return;
    }
    if (code.value !== '123456') {
      uni.showToast({ title: '验证码错误(模拟:123456)', icon: 'none' });
      return;
    }
  } else {
    if (!account.value || !password.value) {
      uni.showToast({ title: '请输入账号和密码', icon: 'none' });
      return;
    }
    if (!(account.value === 'admin' && password.value === '123456')) {
      uni.showToast({ title: '账号或密码错误(模拟:admin/123456)', icon: 'none' });
      return;
    }
  }
  uni.setStorageSync('isLogin', true);
  uni.reLaunch({ url: '/pages/health/index' });
}

function guestLogin() {
  uni.setStorageSync('isLogin', true);
  uni.reLaunch({ url: '/pages/health/index' });
}

function guardianLogin() {
  uni.navigateTo({ url: 'pages/guardian/index' });
}

function showToast(msg) {
  uni.showToast({ title: msg, icon: 'none' });
}

onLoad(() => {
  if (uni.getStorageSync('isLogin')) {
    uni.reLaunch({ url: '/pages/health/index' });
  }
});
</script>

<style scoped>
html, body, .login-bg {
  height: 100vh;
  overflow: hidden;
}
.login-bg {
  min-height: 100vh;
  height: 100vh;
  background: linear-gradient(135deg, #6a8dff 0%, #7ec6f7 100%);
  display: flex;
  flex-direction: column;
  align-items: center;
  padding-top: 40px;
  overflow: hidden;
}
.login-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 32px;
}
.login-title {
  font-size: 32px;
  font-weight: bold;
  color: #fff;
  margin-bottom: 12px;
}
.login-desc {
  font-size: 18px;
  color: #f0f6ff;
}
.login-card {
  width: 92vw;
  max-width: 420px;
  background: #fff;
  border-radius: 24px;
  box-shadow: 0 4px 32px rgba(0,0,0,0.08);
  padding: 32px 20px 24px 20px;
  display: flex;
  flex-direction: column;
  align-items: stretch;
}
.login-tabs {
  display: flex;
  background: #f5f7fa;
  border-radius: 14px;
  margin-bottom: 24px;
  overflow: hidden;
}
.login-tab {
  flex: 1;
  text-align: center;
  font-size: 20px;
  padding: 16px 0;
  color: #888;
  background: none;
  font-weight: 500;
  cursor: pointer;
  transition: background 0.2s, color 0.2s;
}
.login-tab.active {
  background: #6a8dff;
  color: #fff;
}
.login-input {
  display: flex;
  align-items: center;
  background: #f5f7fa;
  border-radius: 12px;
  padding: 16px 14px;
  font-size: 20px;
  margin-bottom: 18px;
}
.input-icon {
  font-size: 22px;
  margin-right: 10px;
  color: #6a8dff;
}
input {
  border: none;
  outline: none;
  background: transparent;
  font-size: 20px;
  flex: 1;
}
.code-row {
  display: flex;
  align-items: center;
  gap: 10px;
  margin-bottom: 18px;
}
.code-btn {
  background: #6a8dff;
  color: #fff;
  border: none;
  border-radius: 0px;
  padding: 0px 0px;
  font-size: 18px;
  align-self: stretch;
  display: flex;
  align-items: center;
  justify-content: center;
  min-width: 100px;
}
.code-btn:disabled {
  background: #b3c6ff;
  color: #fff;
}
.login-btns-row {
  display: flex;
  gap: 16px;
  margin-top: 10px;
  margin-bottom: 16px;
}
.login-btn, .guest-btn {
  flex: 1;
  width: 100%;
  border-radius: 18px;
  font-size: 22px;
  font-weight: bold;
  padding: 18px 0;
}
.login-btn {
  background: linear-gradient(90deg, #6a8dff 0%, #7ec6f7 100%);
  color: #fff;
  border: none;
}
.guest-btn {
  background: #e0e0e0;
  color: #888;
  border: none;
}
.guardian-btn {
  background: transparent;
  color: #6a8dff;
  border: 1px solid #6a8dff;
  border-radius: 18px;
  font-size: 22px;
  font-weight: bold;
  padding: 18px 0;
  margin-top: 10px;
}
.login-links {
  display: flex;
  justify-content: space-between;
  margin-top: 8px;
}
.link {
  color: #6a8dff;
  font-size: 18px;
  cursor: pointer;
}
.flex-between {
  display: flex;
  align-items: center;
}
.flex-1 {
  flex: 1;
}
</style> 