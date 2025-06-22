const getBaseUrl = () => {
  let baseUrl = '';
  if (process.env.NODE_ENV === 'development') {
    // 开发环境，使用代理
    baseUrl = '/api';
  } else {
    // 生产环境
    baseUrl = 'https://tiiaozhanbei.vercel.app/api';
  }
  return baseUrl;
};

const request = (options) => {
  return new Promise((resolve, reject) => {
    uni.request({
      ...options,
      url: getBaseUrl() + options.url,
      success: (res) => {
        if (res.statusCode === 200) {
          resolve(res.data);
        } else {
          reject(res);
        }
      },
      fail: (err) => {
        reject(err);
      }
    });
  });
};

export default request; 