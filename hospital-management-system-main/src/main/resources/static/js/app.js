// Toggle sidebar on mobile
const sidebarToggle = document.getElementById('sidebarToggle');
const sidebar = document.getElementById('sidebar');

if (sidebarToggle && sidebar) {
    sidebarToggle.addEventListener('click', () => {
        sidebar.classList.toggle('active');
    });
}

// Simulate loading data
document.addEventListener('DOMContentLoaded', () => {
    // Animate feature cards
    const featureCards = document.querySelectorAll('.feature-card');
    featureCards.forEach((card, index) => {
        setTimeout(() => {
            card.style.opacity = 1;
            card.style.transform = 'translateY(0)';
        }, index * 200);
    });
});

// API functions for patient management
const PatientAPI = {
    getAll: function() {
        return fetch('/api/patients')
            .then(response => response.json())
            .catch(error => console.error('Error fetching patients:', error));
    },

    getById: function(id) {
        return fetch(`/api/patients/${id}`)
            .then(response => response.json())
            .catch(error => console.error('Error fetching patient:', error));
    },

    create: function(patient) {
        return fetch('/api/patients', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(patient)
        })
        .then(response => response.json())
        .catch(error => console.error('Error creating patient:', error));
    },

    update: function(id, patient) {
        return fetch(`/api/patients/${id}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(patient)
        })
        .then(response => response.json())
        .catch(error => console.error('Error updating patient:', error));
    },

    delete: function(id) {
        return fetch(`/api/patients/${id}`, {
            method: 'DELETE'
        })
        .catch(error => console.error('Error deleting patient:', error));
    },

    search: function(lastName) {
        return fetch(`/api/patients/search?lastName=${encodeURIComponent(lastName)}`)
            .then(response => response.json())
            .catch(error => console.error('Error searching patients:', error));
    }
};