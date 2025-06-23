<template>
	<view class="container">
		<view class="title">服药提醒设置</view>
		
		<view class="reminder-list">
			<view v-for="(reminder, index) in reminders" :key="index" class="reminder-item">
				<view class="reminder-info">
					<view class="med-name">{{ reminder.name }}</view>
					<view class="med-time">{{ reminder.time }} - {{ reminder.frequency }}</view>
				</view>
				<switch :checked="reminder.enabled" @change="toggleReminder(index)" />
			</view>
		</view>

		<view class="add-button-container">
			<button class="add-button" @click="addReminder">添加提醒</button>
		</view>
	</view>
</template>

<script>
	import { getReminders, updateReminder } from '../../utils/medicationStore.js';

	export default {
		data() {
			return {
				reminders: []
			}
		},
		onShow() {
			this.reminders = getReminders();
		},
		methods: {
			toggleReminder(index) {
				const reminder = this.reminders[index];
				reminder.enabled = !reminder.enabled;
				updateReminder(reminder);
				// 小优化：直接在本地更新，避免重新获取列表
				this.$set(this.reminders, index, reminder); 
			},
			addReminder() {
				uni.showToast({
					title: '功能开发中',
					icon: 'none'
				});
			}
		}
	}
</script>

<style scoped>
	.container {
		padding: 20px;
		background-color: #f5f5f5;
		min-height: 100vh;
	}
	.title {
		font-size: 24px;
		font-weight: bold;
		text-align: center;
		margin-bottom: 20px;
	}
	.reminder-list {
		background-color: #ffffff;
		border-radius: 8px;
		overflow: hidden;
	}
	.reminder-item {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 15px;
		border-bottom: 1px solid #eee;
	}
  .reminder-item:last-child {
    border-bottom: none;
  }
	.reminder-info {
		
	}
	.med-name {
		font-size: 16px;
		font-weight: 500;
	}
	.med-time {
		font-size: 14px;
		color: #666;
		margin-top: 5px;
	}
	.add-button-container {
		margin-top: 30px;
	}
	.add-button {
		background-color: #007aff;
		color: white;
		border-radius: 50px;
	}
</style> 