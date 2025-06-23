<template>
	<view class="container">
		<view class="title">医生诊疗记录</view>
		<view class="record-list">
			<view v-for="(record, index) in records" :key="index" class="record-item">
				<view class="record-info">
					<view class="record-title">{{ record.doctor }}（{{ record.department }}）</view>
					<view class="record-meta">{{ record.date }} | 诊断：{{ record.diagnosis }}</view>
				</view>
				<button class="view-btn" @click="viewRecord(record)">查看</button>
			</view>
		</view>
		<view class="add-button-container">
			<button class="add-button" @click="addRecord">添加记录</button>
		</view>
	</view>
</template>

<script>
export default {
	data() {
		return {
			records: [
				{
					id: 1,
					doctor: '王医生',
					department: '心血管内科',
					date: '2025-05-12',
					diagnosis: '高血压随访，血压控制良好'
				},
				{
					id: 2,
					doctor: '李医生',
					department: '内分泌科',
					date: '2025-04-20',
					diagnosis: '糖尿病复查，建议调整饮食'
				},
				{
					id: 3,
					doctor: '张医生',
					department: '骨科',
					date: '2025-03-15',
					diagnosis: '骨质疏松，建议补钙'
				}
			]
		}
	},
	methods: {
		viewRecord(record) {
			uni.showToast({
				title: `查看记录: ${record.doctor}`,
				icon: 'none'
			});
			// 实际项目中，这里会跳转到记录详情页
			// uni.navigateTo({ url: `/pages/records/detail?id=${record.id}` });
		},
		addRecord() {
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
.record-list {
	background-color: #ffffff;
	border-radius: 8px;
	overflow: hidden;
}
.record-item {
	display: flex;
	justify-content: space-between;
	align-items: center;
	padding: 15px;
	border-bottom: 1px solid #eee;
}
.record-item:last-child {
	border-bottom: none;
}
.record-info {
	flex: 1;
	min-width: 0;
	margin-right: 10px;
}
.record-title {
	font-size: 16px;
	font-weight: 500;
}
.record-meta {
	font-size: 14px;
	color: #666;
	margin-top: 5px;
}
.view-btn {
	background-color: #007aff;
	color: white;
	border-radius: 50px;
	font-size: 14px;
	padding: 0 15px;
	flex-shrink: 0;
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