<template>
	<view class="container">
		<view class="title">紧急联系人</view>
		<view class="contact-list">
			<view v-for="(contact, index) in contacts" :key="index" class="contact-item">
				<view class="contact-info">
					<view class="contact-name">{{ contact.name }}</view>
					<view class="contact-relation">{{ contact.relation }}</view>
				</view>
				<button class="call-btn" @click="callContact(contact.phone)">
					<text class="fas fa-phone"></text> 呼叫
				</button>
			</view>
		</view>

		<view class="add-button-container">
			<button class="add-button" @click="addContact">添加联系人</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				contacts: [
					{
						name: '张三',
						relation: '儿子',
						phone: '13800138000'
					},
					{
						name: '社区服务中心',
						relation: '社区',
						phone: '010-12345678'
					},
					{
						name: '李医生',
						relation: '家庭医生',
						phone: '13900139000'
					}
				]
			}
		},
		methods: {
			callContact(phoneNumber) {
				uni.makePhoneCall({
					phoneNumber: phoneNumber
				});
			},
			addContact() {
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
	.contact-list {
		background-color: #ffffff;
		border-radius: 8px;
		overflow: hidden;
	}
	.contact-item {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 15px;
		border-bottom: 1px solid #eee;
	}
	.contact-item:last-child {
		border-bottom: none;
	}
	.contact-info {
		
	}
	.contact-name {
		font-size: 16px;
		font-weight: 500;
	}
	.contact-relation {
		font-size: 14px;
		color: #666;
		margin-top: 5px;
	}
	.call-btn {
		background-color: #e54d42;
		color: white;
		border-radius: 50px;
		font-size: 14px;
		padding: 0 15px;
		display: flex;
		align-items: center;
		gap: 5px;
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