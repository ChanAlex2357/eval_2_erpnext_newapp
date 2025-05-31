# Frappe Doc

## Introduction

Voici une documentation concise pour comprendre rapidement les principaux modules et méthodes de Frappe/ERPNext :

---

### **1. `frappe.db` (Gestion de base de données)**

#### **Méthodes principales**

| Méthode | Description | Retour |
|---------|-------------|--------|
| `frappe.db.get_value(doctype, name, fieldname)` | Récupère une valeur | `String`/`Number` |
| `frappe.db.get_list(doctype, filters, fields)` | Liste filtrée | `List[Dict]` |
| `frappe.db.set_value(doctype, name, fieldname, value)` | Met à jour un champ | `None` |
| `frappe.db.exists(doctype, name)` | Vérifie l'existence | `Boolean` |
| `frappe.db.commit()` | Valide les transactions | `None` |

#### **Exemples**

```python
# Récupérer un champ
customer_name = frappe.db.get_value("Customer", "CUST-001", "customer_name")

# Liste filtrée
invoices = frappe.db.get_list("Sales Invoice", 
    filters={"customer": "CUST-001"}, 
    fields=["name", "grand_total"]
)
```

---

### **2. `frappe.get_doc()` (Gestion des documents)**

#### **Attributs clés**

| Attribut | Description |
|----------|-------------|
| `doc.name` | ID du document |
| `doc.doctype` | Type de document |
| `doc.insert()` | Crée le document |
| `doc.save()` | Sauvegarde les modifications |
| `doc.submit()` | Soumet le document |
| `doc.cancel()` | Annule le document |

#### **Exemples**

```python
# Créer un document
customer = frappe.get_doc({
    "doctype": "Customer",
    "customer_name": "Nouveau Client"
})
customer.insert()  # Persiste en DB

# Charger un document
invoice = frappe.get_doc("Sales Invoice", "INV-2023-0001")
print(invoice.grand_total)
```

---

### **3. `frappe.whitelist` (Sécurité API)**

Pour exposer des méthodes aux appels API :

```python
@frappe.whitelist()
def create_task(subject):
    task = frappe.get_doc({
        "doctype": "Task",
        "subject": subject
    })
    task.insert()
    return task.name
```

---

### **4. Retours communs**

| Type | Description |
|------|-------------|
| `Document` | Objet document avec ses champs |
| `Dict` | Dictionnaire de données |
| `List[Dict]` | Liste de résultats |
| `None` | Actions sans retour |

---

### **5. Bonnes pratiques**

1. **Transactions** :

   ```python
   frappe.db.begin()
   try:
       doc.save()
       frappe.db.commit()
   except:
       frappe.db.rollback()
   ```

2. **Performances** :

   ```python
   # Éviter les boucles de db.set_value()
   frappe.db.bulk_update(docs)
   ```

3. **Debugging** :

   ```python
   frappe.log_error("Message d'erreur")  # Log dans Error Log
   ```

---

### **6. Workflow typique**

```python
# 1. Création
doc = frappe.get_doc({
    "doctype": "Lead",
    "lead_name": "Test"
})
doc.insert()  # → Retourne l'objet créé

# 2. Mise à jour
doc = frappe.get_doc("Lead", "LEAD-001")
doc.status = "Qualified"
doc.save()  # → Retourne None

# 3. Soumission
doc.submit()  # → Lance les validations
```

---

### **Résumé visuel**

```
frappe.db       → Requêtes directes
frappe.get_doc  → Gestion des documents
frappe.whitelist → Sécurisation API
```

Cette documentation couvre 80% des cas d'usage. Pour aller plus loin, consultez [la documentation officielle](https://frappeframework.com/docs).
