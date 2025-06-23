<template>
	<view class="chat-container">
		<view class="chat-header">
			<text class="header-title">王医生（心血管内科）</text>
			<text class="header-status">在线</text>
		</view>
		<scroll-view class="chat-history" scroll-y="true" :scroll-top="scrollTop">
			<view v-for="(message, index) in messages" :key="index" :class="['chat-message', message.sender]">
				<view class="message-bubble">{{ message.content }}</view>
			</view>
		</scroll-view>
		<view class="chat-input-area">
			<input v-model="inputText" class="chat-input" type="text" placeholder="请输入您的问题..." />
			<button class="send-btn" @click="sendMessage" :disabled="!inputText.trim()">发送</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				inputText: '',
				scrollTop: 0,
				messages: [
					{ sender: 'doctor', content: '您好，李大爷。请问有什么可以帮您的吗？' },
				]
			}
		},
		methods: {
			sendMessage() {
				if (!this.inputText.trim()) return;

				this.messages.push({
					sender: 'user',
					content: this.inputText
				});
				this.inputText = '';
				
				// 滚动到底部
				this.$nextTick(() => {
					this.scrollToBottom();
				});

				// 模拟医生回复
				setTimeout(() => {
					this.messages.push({
						sender: 'doctor',
						content: '收到，正在为您分析，请稍候...'
					});
					this.$nextTick(() => {
						this.scrollToBottom();
					});
				}, 1500);
			},
			scrollToBottom() {
				this.scrollTop = this.messages.length * 200; // 一个粗略的估算值
			}
		},
		mounted() {
			this.scrollToBottom();
		}
	}
</script>

<style scoped>
	.chat-container {
		display: flex;
		flex-direction: column;
		height: 100vh;
		background-color: #f5f5f5;
	}
	.chat-header {
		padding: 10px 15px;
		background-color: #ffffff;
		border-bottom: 1px solid #eee;
		text-align: center;
	}
	.header-title {
		font-size: 16px;
		font-weight: bold;
	}
	.header-status {
		font-size: 12px;
		color: #1aad19;
		margin-left: 8px;
	}
	.chat-history {
		flex: 1;
		padding: 15px;
		overflow-y: auto;
		padding-bottom: 80px;
		padding-bottom: calc(80px + constant(safe-area-inset-bottom));
		padding-bottom: calc(80px + env(safe-area-inset-bottom));
	}
	.chat-message {
		display: flex;
		margin-bottom: 15px;
	}
	.chat-message.user {
		justify-content: flex-end;
	}
	.chat-message.doctor {
		justify-content: flex-start;
	}
	.message-bubble {
		max-width: 70%;
		padding: 10px 15px;
		border-radius: 18px;
		font-size: 15px;
		line-height: 1.5;
	}
	.chat-message.user .message-bubble {
		background-color: #007aff;
		color: white;
	}
	.chat-message.doctor .message-bubble {
		background-color: #ffffff;
		color: #333;
	}
	.chat-input-area {
		display: flex;
		padding: 10px;
		background-color: #ffffff;
		border-top: 1px solid #eee;
		align-items: center;
		position: fixed;
		bottom: 0;
		left: 0;
		right: 0;
		z-index: 100;
		padding-bottom: calc(10px + constant(safe-area-inset-bottom));
		padding-bottom: calc(10px + env(safe-area-inset-bottom));
	}
	.chat-input {
		flex: 1;
		height: 40px;
		background-color: #f5f5f5;
		border-radius: 20px;
		padding: 0 15px;
		font-size: 15px;
	}
	.send-btn {
		margin-left: 10px;
		background-color: #007aff;
		color: white;
		border-radius: 20px;
		font-size: 15px;
		padding: 0 20px;
		height: 40px;
		line-height: 40px;
	}
	.send-btn[disabled] {
		background-color: #cce4ff;
	}
</style> 