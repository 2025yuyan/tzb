/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./index.html",
    "./src/**/*.{vue,js,ts,jsx,tsx}",
  ],
  theme: {
    extend: {
      fontSize: {
        xs: '1rem',      // 原默认 '0.75rem'
        sm: '1.5rem',        // 原默认 '0.875rem'
        base: '1.2rem',    // 原默认 '1rem'
        lg: '1.5rem',     // 原默认 '1.125rem'
        xl: '1.5rem',    // 原默认 '1.25rem'
        '2xl': '1.8rem',   // 原默认 '1.5rem'
        '3xl': '2.25rem',     // 原默认 '1.875rem'
      },
      colors: {
        primary: '#4F46E5',
        secondary: '#3B82F6',
        accent: '#F59E0B',
        success: '#10B981',
        warning: '#F59E0B',
        danger: '#EF4444',
        neutral: '#F3F4F6',
        light: '#FFF7ED',
        dark: '#1F2937',
      },
      fontFamily: {
        sans: ['Inter', 'system-ui', 'sans-serif'],
      },
    },
  },
  plugins: [],
} 