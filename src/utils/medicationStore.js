const STORAGE_KEY = 'medication_reminders';

// 默认的提醒数据
const defaultReminders = [
	{
		id: 1,
		name: '阿司匹林',
		time: '08:00',
		frequency: '每天',
		notes: '饭后服用',
		enabled: true
	},
	{
		id: 2,
		name: '降压药',
		time: '12:30',
		frequency: '每天',
		notes: '每日一次',
		enabled: true
	},
	{
		id: 3,
		name: '维生素 D',
		time: '20:00',
		frequency: '每周一、三、五',
		notes: '随餐服用',
		enabled: false
	}
];

// 获取所有用药提醒
export function getReminders() {
	const reminders = uni.getStorageSync(STORAGE_KEY);
	if (reminders && reminders.length > 0) {
		return reminders;
	} else {
		// 如果本地存储没有，则使用默认数据并存入本地
		uni.setStorageSync(STORAGE_KEY, defaultReminders);
		return defaultReminders;
	}
}

// 保存所有用药提醒
export function saveReminders(reminders) {
	uni.setStorageSync(STORAGE_KEY, reminders);
}

// 更新单个提醒
export function updateReminder(updatedReminder) {
	let reminders = getReminders();
	const index = reminders.findIndex(r => r.id === updatedReminder.id);
	if (index !== -1) {
		reminders[index] = updatedReminder;
		saveReminders(reminders);
	}
}

// 添加新提醒 (为将来功能预留)
export function addReminder(newReminder) {
	let reminders = getReminders();
	newReminder.id = Date.now(); // 使用时间戳作为简单ID
	reminders.push(newReminder);
	saveReminders(reminders);
} 