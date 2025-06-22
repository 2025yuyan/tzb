<template>
  <section class="page-transition">

    <div class="mb-1 bg-gradient-to-r from-primary/10 to-secondary/10 rounded-xl p-1">
      <p class="max-w-2xl mx-auto" style="text-align: center; color: #6B7280;">获取专业的老年人健康护理知识和建议</p>
    </div>
    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">热门文章</h2>
      <div class="articles-container">
        <a href="#" class="article-card">
          <div>
            <h3 class="font-medium mb-1">老年人日常健康管理指南</h3>
            <p class="text-sm text-gray-500 mb-2 line-clamp-2">了解老年人日常健康管理的关键点，包括饮食、运动、心理调节等方面的专业建议。</p>
            <div class="text-xs text-gray-400">2025-05-15 · 阅读 2.3k</div>
          </div>
        </a>
        <a href="#" class="article-card">
          <div>
            <h3 class="font-medium mb-1">老年人心血管疾病预防与护理</h3>
            <p class="text-sm text-gray-500 mb-2 line-clamp-2">心血管疾病是老年人常见疾病之一，本文介绍了如何通过生活方式调整和家庭护理降低患病风险。</p>
            <div class="text-xs text-gray-400">2025-05-10 · 阅读 1.8k</div>
          </div>
        </a>
        <a href="#" class="article-card">
          <div>
            <h3 class="font-medium mb-1">老年人认知障碍早期识别与干预</h3>
            <p class="text-sm text-gray-500 mb-2 line-clamp-2">了解认知障碍的早期症状和家庭干预方法，帮助老年人维持大脑健康，延缓认知功能下降。</p>
            <div class="text-xs text-gray-400">2025-05-05 · 阅读 1.5k</div>
          </div>
        </a>
      </div>
      <div class="text-center mt-4">
        <button class="bg-white hover:bg-gray-100 text-primary border border-primary font-medium py-2 px-4 rounded-lg transition-all duration-300">查看更多文章</button>
      </div>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">专家课程</h2>
      <div class="courses-container">
        <div class="course-card">
          <a href="#" class="course-card-link">
            <h3 class="font-medium mb-1">老年人日常护理技巧</h3>
            <p class="text-sm text-gray-500 mb-2">由资深护理专家讲解老年人日常生活护理的实用技巧和注意事项。</p>
            <div class="course-card-footer">
              <div class="text-xs text-gray-400">8课时 · 初级</div>
              <div class="text-xs bg-primary/10 text-primary px-2 py-1 rounded-full">免费</div>
            </div>
          </a>
        </div>
        <div class="course-card">
          <a href="#" class="course-card-link">
            <h3 class="font-medium mb-1">老年人心血管健康管理</h3>
            <p class="text-sm text-gray-500 mb-2">心血管专家分享老年人心血管健康管理的最新理念和方法。</p>
            <div class="course-card-footer">
              <div class="text-xs text-gray-400">12课时 · 中级</div>
              <div class="text-xs bg-success/10 text-success px-2 py-1 rounded-full">进行中</div>
            </div>
          </a>
        </div>
      </div>
    </section>

    <section class="bg-white rounded-xl shadow-sm p-6 mb-8">
      <h2 class="text-xl font-bold mb-4">常见问题</h2>
      <div class="space-y-4">
        <div class="bg-gray-50 rounded-lg overflow-hidden">
          <button class="w-full flex justify-between items-center p-4 text-left focus:outline-none faq-toggle" @click="toggleFaq(0)">
            <span class="font-medium">如何判断老年人是否需要专业护理？</span>
            <i class="fa-solid fa-chevron-down text-gray-400 transition-transform duration-300" :class="{'rotate-180': openFaqs[0]}"></i>
          </button>
          <div class="px-4 pb-4" v-show="openFaqs[0]">
            <p class="text-sm text-gray-500">判断老年人是否需要专业护理需要综合考虑多个因素，包括日常生活能力、健康状况、认知能力等。一般来说，如果老年人出现以下情况，可能需要考虑专业护理：</p>
            <ul class="text-sm text-gray-500 mt-2 list-disc pl-5 space-y-1">
              <li>日常生活能力明显下降，如穿衣、洗澡、进食等需要帮助</li>
              <li>患有慢性疾病需要定期医疗护理和监测</li>
              <li>出现认知障碍，如记忆力减退、定向力障碍等</li>
              <li>有跌倒风险或曾发生过跌倒事件</li>
              <li>心理健康问题，如抑郁、焦虑等</li>
            </ul>
          </div>
        </div>
        <div class="bg-gray-50 rounded-lg overflow-hidden">
          <button class="w-full flex justify-between items-center p-4 text-left focus:outline-none faq-toggle" @click="toggleFaq(1)">
            <span class="font-medium">老年人应该如何合理饮食？</span>
            <i class="fa-solid fa-chevron-down text-gray-400 transition-transform duration-300" :class="{'rotate-180': openFaqs[1]}"></i>
          </button>
          <div class="px-4 pb-4" v-show="openFaqs[1]">
            <p class="text-sm text-gray-500">老年人合理饮食应遵循以下原则：</p>
            <ul class="text-sm text-gray-500 mt-2 list-disc pl-5 space-y-1">
              <li>食物多样，谷类为主，粗细搭配</li>
              <li>多吃蔬菜水果和薯类</li>
              <li>每天吃奶类、大豆或其制品</li>
              <li>常吃适量的鱼、禽、蛋和瘦肉</li>
              <li>减少烹调油用量，吃清淡少盐膳食</li>
              <li>食不过量，天天运动，保持健康体重</li>
              <li>三餐分配要合理，零食要适当</li>
              <li>每天足量饮水，合理选择饮料</li>
              <li>如饮酒应限量</li>
              <li>吃新鲜卫生的食物</li>
            </ul>
          </div>
        </div>
        <div class="bg-gray-50 rounded-lg overflow-hidden">
          <button class="w-full flex justify-between items-center p-4 text-left focus:outline-none faq-toggle" @click="toggleFaq(2)">
            <span class="font-medium">如何帮助老年人保持心理健康？</span>
            <i class="fa-solid fa-chevron-down text-gray-400 transition-transform duration-300" :class="{'rotate-180': openFaqs[2]}"></i>
          </button>
          <div class="px-4 pb-4" v-show="openFaqs[2]">
            <p class="text-sm text-gray-500">帮助老年人保持心理健康可以从以下几个方面入手：</p>
            <ul class="text-sm text-gray-500 mt-2 list-disc pl-5 space-y-1">
              <li>保持社交活动：鼓励老年人参加社区活动、兴趣小组等，与同龄人交流</li>
              <li>提供情感支持：倾听老年人的心声，给予关心和理解</li>
              <li>鼓励适当运动：运动有助于缓解压力，改善情绪</li>
              <li>培养兴趣爱好：帮助老年人找到自己感兴趣的事情，如绘画、书法、园艺等</li>
              <li>保持良好睡眠：良好的睡眠对心理健康至关重要</li>
              <li>帮助建立生活目标：让老年人感到自己仍然有价值和作用</li>
              <li>及时寻求专业帮助：如果老年人出现严重的心理问题，应及时寻求心理咨询或治疗</li>
            </ul>
          </div>
        </div>
      </div>
    </section>
  </section>
</template>

<script setup>
import { ref } from 'vue';

const openFaqs = ref([false, false, false]);

function toggleFaq(index) {
  openFaqs.value[index] = !openFaqs.value[index];
}
</script>

<style scoped>
.articles-container .article-card:not(:last-child) {
  margin-bottom: 1rem; /* 16px, replacement for space-y-4 */
}
.article-card {
  display: block;
  background-color: #F9FAFB; /* bg-gray-50 */
  border-radius: 0.5rem; /* rounded-lg */
  padding: 1rem; /* p-4 */
  transition: all 0.3s;
}
.article-card:hover {
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* hover:shadow-lg */
  transform: translateY(-0.25rem); /* hover:-translate-y-1 */
}

.courses-container {
  display: flex;
  flex-wrap: wrap;
  justify-content: space-between;
}
.course-card {
  width: 100%;
  box-sizing: border-box;
  margin-bottom: 1rem;
}
.course-card-link {
  display: flex;
  flex-direction: column;
  height: 100%;
  background-color: #F9FAFB; /* bg-gray-50 */
  border-radius: 0.5rem; /* rounded-lg */
  padding: 1rem; /* p-4 */
  transition: all 0.3s;
}
.course-card-link:hover {
  box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1), 0 2px 4px -1px rgba(0, 0, 0, 0.06); /* hover:shadow-lg */
  transform: translateY(-0.25rem); /* hover:-translate-y-1 */
}
.course-card-footer {
  margin-top: auto;
  display: flex;
  justify-content: space-between;
  align-items: center;
}
/* For medium screens and up, equivalent to md: in Tailwind */
@media (min-width: 768px) {
  .course-card {
    width: calc(50% - 0.5rem); /* Each card takes half the width minus half the desired gap */
    margin-bottom: 0;
  }
}

.text-success {
    color: #10B981;
}
.rotate-180 {
  transform: rotate(180deg);
}
</style> 