<template>
	<view class="container">
		<view class="title">预约挂号</view>

		<form @submit.prevent="submitBooking">
			<view class="form-group">
				<label class="form-label">就诊医院</label>
				<picker @change="bindPickerChange($event, 'hospitalIndex')" :value="hospitalIndex" :range="hospitals" range-key="name">
					<view class="picker">
						{{ hospitals[hospitalIndex].name }}
						<text class="fas fa-chevron-right icon-arrow"></text>
					</view>
				</picker>
			</view>

			<view class="form-group">
				<label class="form-label">就诊科室</label>
				<picker @change="bindPickerChange($event, 'departmentIndex')" :value="departmentIndex" :range="departments" range-key="name">
					<view class="picker">
						{{ departments[departmentIndex].name }}
						<text class="fas fa-chevron-right icon-arrow"></text>
					</view>
				</picker>
			</view>

			<view class="form-group">
				<label class="form-label">就诊日期</label>
				<picker mode="date" :value="date" :start="startDate" @change="bindDateChange">
					<view class="picker">
						{{ date }}
						<text class="fas fa-chevron-right icon-arrow"></text>
					</view>
				</picker>
			</view>
			
			<view class="form-group">
				<label class="form-label">就诊人</label>
				<input class="input-field" type="text" v-model="patientName" placeholder="请输入就诊人姓名" />
			</view>

			<view class="submit-btn-container">
				<button class="submit-btn" form-type="submit">确认挂号</button>
			</view>
		</form>
	</view>
</template>

<script>
	export default {
		data() {
			const currentDate = new Date();
			return {
				hospitals: [
					{ id: 1, name: '市第一人民医院' },
					{ id: 2, name: '市中医院' },
					{ id: 3, name: '区中心医院' }
				],
				hospitalIndex: 0,
				departments: [
					{ id: 1, name: '心血管内科' },
					{ id: 2, name: '呼吸科' },
					{ id: 3, name: '骨科' },
					{ id: 4, name: '神经内科' }
				],
				departmentIndex: 0,
				date: this.getFormattedDate(currentDate),
				patientName: '李大爷', // 默认值
			}
		},
		computed: {
			startDate() {
				return this.getFormattedDate(new Date());
			}
		},
		methods: {
			getFormattedDate(date) {
				const year = date.getFullYear();
				const month = (date.getMonth() + 1).toString().padStart(2, '0');
				const day = date.getDate().toString().padStart(2, '0');
				return `${year}-${month}-${day}`;
			},
			bindPickerChange(e, type) {
				this[type] = e.detail.value;
			},
			bindDateChange(e) {
				this.date = e.detail.value;
			},
			submitBooking() {
				uni.showLoading({
					title: '正在提交...'
				});

				setTimeout(() => {
					uni.hideLoading();
					uni.showToast({
						title: '预约成功',
						icon: 'success'
					});
					
					// 实际项目中会在这里调用接口
					console.log({
						hospital: this.hospitals[this.hospitalIndex],
						department: this.departments[this.departmentIndex],
						date: this.date,
						patient: this.patientName
					});

				}, 1000);
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
		margin-bottom: 25px;
	}
	.form-group {
		background-color: #ffffff;
		padding: 15px;
		border-radius: 8px;
		margin-bottom: 15px;
		display: flex;
		justify-content: space-between;
		align-items: center;
	}
	.form-label {
		font-size: 16px;
		font-weight: 500;
	}
	.picker, .input-field {
		font-size: 16px;
		color: #666;
		text-align: right;
	}
	.input-field {
		width: 60%;
	}
	.icon-arrow {
		margin-left: 5px;
		color: #ccc;
	}
	.submit-btn-container {
		margin-top: 30px;
	}
	.submit-btn {
		background-color: #007aff;
		color: white;
		border-radius: 50px;
		font-size: 16px;
	}
</style> 