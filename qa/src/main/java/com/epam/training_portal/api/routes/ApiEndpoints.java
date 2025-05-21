package com.epam.training_portal.api.routes;

/**
 * This class centralizes all API endpoints for easier management.
 * Use nested static classes to group endpoints based on functionality or modules.
 */
public class ApiEndpoints {

    private ApiEndpoints() {
        // Prevent instantiation
    }

    // Static class for Dashboard-related endpoints
    public static class Dashboard {
        public static final String ANALYTICS = "/api/dashboard/analytics"; // Fixed endpoint for Analytics API
        public static final String METRICS = "/api/dashboard/metrics"; // Fixed endpoint for Metrics API
        public static final String INVALID_ANALYTICS = "/api/dashboard/invalid"; // Invalid endpoint for negative tests
        public static final String PENDING_FEEDBACK_EVENTS = "/api/Dashboard/pending-feedback-events";
        public static final String FEEDBACK_SENT_EVENTS_BY_NAME = "/api/Dashboard/feedback-sent-events-by-name";
        public static final String FEEDBACK_SENT_EVENTS = "/api/Dashboard/feedback-sent-events";
        public static final String PENDING_FEEDBACK_EVENTS_BY_NAME = "/api/Dashboard/pending-feedback-events-by-name";

    }
    public static class BenchLeaderBoard {
        public static final String LEADERBOARD_BENCH = "/api/Leaderboard/bench";
        public static final String SCHEDULED_EVENT = "/api/ScheduledEvent/getAllDomains";
        public static final String CURRENT_WEIGHTAGES = "/api/Weightage/event-weightages";
        public static final String UPDATED_WEIGHTAGES = "/api/Weightage/update-weightages";
        public static final String EXPORT_BENCH = "/api/Leaderboard/bench/export";

    }

    // Static class for Attendance Reports-related endpoints
    public static class AttendanceReports {
        public static final String GET_EMPLOYEE = "/api/Employee";
        public static final String QR_GENERATION = "/api/qrcode/generate";
        public static final String DOWNLOAD_ATTENDANCE_REPORTS = "/AttendanceReports/by-schedule-name/{scheduleEventName}/excel";
        public static final String MARK_ATTENDANCE = "/AttendanceReports/mark";
        public static final String UNMARK_ATTENDANCE = "/AttendanceReports/unmark";
        public static final String ADD_EMPLOYEE = "/Employee/api";
    }
}


