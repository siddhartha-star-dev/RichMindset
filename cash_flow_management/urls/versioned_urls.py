from django.urls import include, path
from rest_framework.routers import SimpleRouter

from cash_flow_management.apps.accounts.views import ProfileViewSet
from cash_flow_management.apps.Rich_Mindset.views import TransactionViewSet

router = SimpleRouter()
router.register("profile", ProfileViewSet, basename="profile")
router.register("Transactions", TransactionViewSet, basename="transactions")

versioned_urls = [path("", include(router.urls))]
