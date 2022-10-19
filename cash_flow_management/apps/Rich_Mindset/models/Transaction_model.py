from datetime import date

from django.db import models
from django.utils.translation import gettext_lazy as _

from cash_flow_management.apps.accounts.models.user_models import User


class Categories(models.TextChoices):
    FOOD = "Food", _("Food")
    FASHION = "Fashion", _("Fashion")
    HEALTH = "Health", _("Health")
    FINANCE = "FINANCE", _("Finance")
    ASSET = "ASSET", _("Asset(car/house etc)")
    OTHER = "OTHER", _("Other")


def directory_path(instance, filename):
    return f"receipts/{instance.id}/{instance.category}/{filename}"


class Transaction(models.Model):
    user = models.ForeignKey(to=User, on_delete=models.CASCADE)
    category = models.CharField(choices=Categories.choices, max_length=10)
    date = models.DateField(default=date.today)
    expenditure = models.IntegerField(blank=True, null=True)
    receipt = models.ImageField(
        upload_to=directory_path, blank=True, null=True
    )

    class Meta:
        constraints = [
            models.CheckConstraint(
                check=models.Q(expenditure__isnull=False)
                | models.Q(receipt__isnull=False),
                name="not_both_null",
            )
        ]
