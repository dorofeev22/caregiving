INSERT INTO caregiving.form_info(info)
	VALUES (
    '{
    "fileds": [
        {
            "name": "name",
            "type": "input",
            "valueType": "string",
            "label": "Full name",
            "validationRules": [
                {
                    "type": "mandatory",
                    "value": "true"
                },
                {
                    "message": "Only latin characters",
                    "type": "regexp",
                    "value": "[A-Za-z]*"
                },
                {
                    "type": "minLength",
                    "value": 4
                }
            ],
            "placeholder": "Enter your name"
        },
        {
            "name": "food",
            "type": "select",
            "label": "Favourite Food",
            "validationRules": [
                {
                    "type": "mandatory",
                    "value": "true"
                }
            ],
            "placeholder": "Select an option",
            "options": [
                {
                    "value": "admin",
                    "label": "Admin"
                },
                {
                    "value": "user",
                    "label": "User"
                }
            ]
        },
        {
            "valueType": "boolean",
            "type": "input",
            "name": "che",
            "label": "chekL"
        },
        {
            "validationRules": [
                {
                    "type": "minVal",
                    "value": 0
                }
            ],
            "valueType": "number",
            "type": "input",
            "name": "number",
            "label": "Number"
        },
        {
            "type": "button",
            "name": "submit",
            "label": "Submit"
        }
    ],
    "name": "Vasya"
}'
    );