const API_BASE = '/api';

const api = {
    async post(endpoint, data) {
        const response = await fetch(`${API_BASE}${endpoint}`, {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            const error = await response.text();
            throw new Error(error || 'Something went wrong');
        }
        return response.json();
    },

    async get(endpoint) {
        const response = await fetch(`${API_BASE}${endpoint}`);
        if (!response.ok) {
            throw new Error('Failed to fetch data');
        }
        return response.json();
    },

    // Auth Helpers
    async register(userData) {
        return this.post('/auth/register', userData);
    },

    async login(credentials) {
        const user = await this.post('/auth/login', credentials);
        localStorage.setItem('pulse_user', JSON.stringify(user));
        return user;
    },

    logout() {
        localStorage.removeItem('pulse_user');
        window.location.href = 'index.html';
    },

    getCurrentUser() {
        const user = localStorage.getItem('pulse_user');
        return user ? JSON.parse(user) : null;
    }
};

// Toast Notification Helper
const showToast = (message, type = 'success') => {
    const toast = document.createElement('div');
    toast.className = `toast ${type} glass`;
    toast.innerText = message;
    document.body.appendChild(toast);

    // Add toast styles dynamically if not in CSS
    Object.assign(toast.style, {
        position: 'fixed',
        bottom: '20px',
        right: '20px',
        padding: '15px 25px',
        borderRadius: '8px',
        zIndex: '2000',
        color: '#fff',
        borderLeft: type === 'success' ? '5px solid #ccff00' : '5px solid #ff6b00',
        animation: 'slideIn 0.3s ease forwards'
    });

    setTimeout(() => {
        toast.style.opacity = '0';
        setTimeout(() => toast.remove(), 500);
    }, 3000);
};
