from .base import Common
from .environment import env


class Prod(Common):
    # Keep debug true in development
    DEBUG = False

    # Restrict the allowed hosts
    ALLOWED_HOSTS = env.list(
        "CASH_FLOW_MANAGEMENT_PROD_ALLOWED_HOSTS",
        default=[
            "127.0.0.1",
            "localhost",
        ],
    )

    # Use local database
    DATABASES = {
        "default": env.db(
            "CASH_FLOW_MANAGEMENT_DATABASE_URL_PROD",
            default="psql://cash_flow_management_user:test_password@127.0.0.1:5432/cash_flow_management_db",
        )
    }

    # Mail
    EMAIL_BACKEND = "django.core.mail.backends.smtp.EmailBackend"
    EMAIL_PORT = env.str(
        "CASH_FLOW_MANAGEMENT_EMAIL_PORT", default="1025"
    )
    EMAIL_HOST = env.str(
        "CASH_FLOW_MANAGEMENT_EMAIL_HOST",
        default="127.0.0.1",
    )
    EMAIL_HOST_USER = env.str(
        "CASH_FLOW_MANAGEMENT_EMAIL_HOST_USER",
        default="user",
    )
    EMAIL_HOST_PASSWORD = env.str(
        "CASH_FLOW_MANAGEMENT_EMAIL_HOST_PASSWORD",
        default="password",
    )
    EMAIL_USE_TLS = env.bool(
        "CASH_FLOW_MANAGEMENT_EMAIL_USE_TLS", default=True
    )

    # Disable CORS check
    CORS_ALLOWED_ORIGINS = env.list(
        "CASH_FLOW_MANAGEMENT_CORS_WHITELIST",
        default=["http://localhost:8000", "http://127.0.0.1:8000"],
    )

    # Celery broker config
    BROKER_URL = env.str(
        "CASH_FLOW_MANAGEMENT_CELERY_BROKER_PROD",
        default="amqp://cash_flow_management:pass@localhost:5672/cash_flow_management",
    )
