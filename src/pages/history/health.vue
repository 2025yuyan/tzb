<template>
	<view class="container">
		<view class="title">健康历史记录</view>

		<view class="chart-section">
			<view class="section-title">健康趋势总览</view>
			<view class="chart-container">
				<v-chart class="chart" :option="chartOption" />
			</view>
		</view>

		<view class="history-section">
			<view class="section-title">每日健康数据</view>
			<view class="history-list">
				<view v-for="(item, index) in historyData" :key="index" class="history-item">
					<view class="item-header">
						<text class="date">{{ item.date }}</text>
						<text :class="['status', item.isNormal ? 'status-normal' : 'status-warn']">{{ item.isNormal ? '正常' : '需关注' }}</text>
					</view>
					<view class="item-body">
						<view class="data-point">心率: {{ item.heartRate }} bpm</view>
						<view class="data-point">血压: {{ item.bloodPressure }} mmHg</view>
						<view class="data-point">睡眠: {{ item.sleepHours }} h</view>
					</view>
				</view>
			</view>
		</view>

	</view>
</template>

<script setup>
	import { ref, onMounted } from 'vue';
	import { use } from 'echarts/core';
	import { CanvasRenderer } from 'echarts/renderers';
	import { LineChart } from 'echarts/charts';
	import { TitleComponent, TooltipComponent, LegendComponent, GridComponent } from 'echarts/components';
	import VChart from 'vue-echarts';

	use([
		CanvasRenderer,
		LineChart,
		TitleComponent,
		TooltipComponent,
		LegendComponent,
		GridComponent
	]);

	const historyData = ref([
		{ date: '2025-05-22', heartRate: 74, bloodPressure: '122/81', sleepHours: 7.0, isNormal: true },
		{ date: '2025-05-21', heartRate: 78, bloodPressure: '128/85', sleepHours: 6.5, isNormal: false },
		{ date: '2025-05-20', heartRate: 72, bloodPressure: '120/80', sleepHours: 8.0, isNormal: true },
		{ date: '2025-05-19', heartRate: 75, bloodPressure: '125/82', sleepHours: 7.2, isNormal: true },
	]);

	const chartOption = ref({});

	onMounted(() => {
		chartOption.value = {
			tooltip: { trigger: 'axis' },
			legend: { data: ['心率', '高压', '低压'] },
			grid: { left: '3%', right: '4%', bottom: '3%', containLabel: true },
			xAxis: {
				type: 'category',
				boundaryGap: false,
				data: ['05-19', '05-20', '05-21', '05-22', '05-23']
			},
			yAxis: { type: 'value' },
			series: [
				{ name: '心率', type: 'line', data: [75, 72, 78, 74, 72] },
				{ name: '高压', type: 'line', data: [125, 120, 128, 122, 120] },
				{ name: '低压', type: 'line', data: [82, 80, 85, 81, 80] }
			]
		};
	});

</script>

<style scoped>
	.container {
		background-color: #f5f5f5;
		min-height: 100vh;
		padding: 15px;
	}
	.title {
		font-size: 24px;
		font-weight: bold;
		text-align: center;
		margin-bottom: 20px;
	}
	.chart-section, .history-section {
		background-color: #ffffff;
		padding: 15px;
		border-radius: 8px;
		margin-bottom: 15px;
	}
	.section-title {
		font-size: 16px;
		font-weight: bold;
		margin-bottom: 15px;
		border-left: 4px solid #007aff;
		padding-left: 10px;
	}
	.chart-container {
		height: 300px;
	}
	.history-item {
		padding: 10px;
		border-bottom: 1px solid #eee;
	}
	.history-item:last-child {
		border-bottom: none;
	}
	.item-header {
		display: flex;
		justify-content: space-between;
		align-items: center;
		margin-bottom: 8px;
	}
	.date {
		font-size: 15px;
		font-weight: 500;
	}
	.status {
		font-size: 12px;
		padding: 2px 8px;
		border-radius: 12px;
		color: white;
	}
	.status-normal {
		background-color: #1aad19;
	}
	.status-warn {
		background-color: #e54d42;
	}
	.item-body {
		display: flex;
		justify-content: space-around;
		font-size: 14px;
		color: #666;
	}
</style> 