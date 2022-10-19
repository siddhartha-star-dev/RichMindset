from rest_framework import serializers

from cash_flow_management.apps.Rich_Mindset.models.Transaction_model import (
    Transaction,
)


class TransactionSerializer(serializers.ModelSerializer):
    class Meta:
        model = Transaction
        exclude = ["user"]
        read_only_fields = ("id",)
