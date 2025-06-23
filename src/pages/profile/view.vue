<template>
	<view class="container">
		<view class="header">
			<view class="user-info">
				<view class="avatar">
					<text class="fas fa-user"></text>
				</view>
				<view class="text-info">
					<view class="name">{{ profile.name }}</view>
					<view class="meta">{{ profile.gender }} | {{ profile.age }}岁</view>
				</view>
			</view>
		</view>

		<view class="profile-section">
			<view class="section-title">基本信息</view>
			<view class="info-grid">
				<view class="info-item">
					<view class="info-label">身高</view>
					<view class="info-value">{{ profile.basicInfo.height }}</view>
				</view>
				<view class="info-item">
					<view class="info-label">体重</view>
					<view class="info-value">{{ profile.basicInfo.weight }}</view>
				</view>
				<view class="info-item">
					<view class="info-label">血型</view>
					<view class="info-value">{{ profile.basicInfo.bloodType }}</view>
				</view>
			</view>
		</view>

		<view class="profile-section">
			<view class="section-title">健康状况</view>
			<view class="info-list">
				<view class="info-row">
					<view class="info-label">过敏史</view>
					<view class="info-value">{{ profile.healthStatus.allergies }}</view>
				</view>
				<view class="info-row">
					<view class="info-label">慢性病</view>
					<view class="info-value">{{ profile.healthStatus.chronicDiseases }}</view>
				</view>
			</view>
		</view>
		
		<view class="profile-section">
			<view class="section-title">最近检查报告</view>
			<view class="report-list">
				<view class="report-item">
					<view class="report-name">年度体检报告</view>
					<view class="report-date">2025-05-10</view>
				</view>
				<view class="report-item">
					<view class="report-name">血常规检查</view>
					<view class="report-date">2025-03-22</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script>
	export default {
		data() {
			return {
				profile: {
					name: '李大爷',
					gender: '男',
					age: 72,
					basicInfo: {
						height: '170 cm',
						weight: '65 kg',
						bloodType: 'A型'
					},
					healthStatus: {
						allergies: '无',
						chronicDiseases: '高血压'
					}
				}
			}
		}
	}
</script>

<style scoped>
	.container {
		background-color: #f5f5f5;
		min-height: 100vh;
	}
	.header {
		background: linear-gradient(135deg, #6a8dff 0%, #7ec6f7 100%);
		padding: 30px 20px 20px;
		color: white;
	}
	.user-info {
		display: flex;
		align-items: center;
	}
	.avatar {
		width: 60px;
		height: 60px;
		border-radius: 50%;
		background-color: rgba(255,255,255,0.3);
		display: flex;
		align-items: center;
		justify-content: center;
		font-size: 30px;
		margin-right: 15px;
	}
	.name {
		font-size: 20px;
		font-weight: bold;
	}
	.meta {
		font-size: 14px;
		opacity: 0.9;
		margin-top: 5px;
	}
	.profile-section {
		background-color: #ffffff;
		margin: 15px;
		padding: 15px;
		border-radius: 8px;
	}
	.section-title {
		font-size: 16px;
		font-weight: bold;
		margin-bottom: 15px;
		border-left: 4px solid #007aff;
		padding-left: 10px;
	}
	.info-grid {
		display: flex;
		justify-content: space-around;
		text-align: center;
	}
	.info-label {
		color: #666;
		font-size: 14px;
		margin-bottom: 5px;
	}
	.info-value {
		font-size: 16px;
	}
	.info-list {
		
	}
	.info-row {
		display: flex;
		justify-content: space-between;
		padding: 10px 0;
		border-bottom: 1px solid #f5f5f5;
	}
	.info-row:last-child {
		border-bottom: none;
	}
	.report-list {
		
	}
	.report-item {
		display: flex;
		justify-content: space-between;
		padding: 10px 0;
		font-size: 15px;
	}
	.report-date {
		color: #666;
	}
</style> 