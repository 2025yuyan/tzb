<template>
	<view class="container">
		<view class="title">医院检查报告</view>
		<view class="report-list">
			<view v-for="(report, index) in reports" :key="index" class="report-item">
				<view class="report-info">
					<view class="report-title">{{ report.title }}</view>
					<view class="report-meta">{{ report.hospital }} | {{ report.date }}</view>
				</view>
				<button class="view-btn" @click="viewReport(report)">查看</button>
			</view>
		</view>

		<view class="add-button-container">
			<button class="add-button" @click="addReport">添加报告</button>
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				reports: [
					{
						id: 1,
						title: '年度全面体检报告',
						hospital: '市第一人民医院',
						date: '2025-05-10'
					},
					{
						id: 2,
						title: '血常规检查报告',
						hospital: '市第一人民医院',
						date: '2025-03-22'
					},
					{
						id: 3,
						title: '心脏彩超检查报告',
						hospital: '市中医院',
						date: '2024-12-15'
					}
				]
			}
		},
		methods: {
			viewReport(report) {
				uni.showToast({
					title: `查看报告: ${report.title}`,
					icon: 'none'
				});
				// 实际项目中，这里会跳转到报告详情页
				// uni.navigateTo({ url: `/pages/reports/detail?id=${report.id}` });
			},
			addReport() {
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
	.report-list {
		background-color: #ffffff;
		border-radius: 8px;
		overflow: hidden;
	}
	.report-item {
		display: flex;
		justify-content: space-between;
		align-items: center;
		padding: 15px;
		border-bottom: 1px solid #eee;
	}
	.report-item:last-child {
		border-bottom: none;
	}
	.report-info {
		flex: 1;
		min-width: 0;
		margin-right: 10px;
	}
	.report-title {
		font-size: 16px;
		font-weight: 500;
	}
	.report-meta {
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