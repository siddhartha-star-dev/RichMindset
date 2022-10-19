from django.contrib import admin

from cash_flow_management.apps.Rich_Mindset.models.Transaction_model import (
    Transaction,
)


@admin.register(Transaction)
class TransactionsAdmin(admin.ModelAdmin):
    list_display = ("id", "category", "date")
