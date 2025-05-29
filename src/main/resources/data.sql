-- Initialize deductions with updated rates
INSERT INTO deductions (code, deduction_name, percentage) VALUES
('TAX', 'Employee Tax', 30.0),
('PENSION', 'Pension', 5.0),
('MEDICAL', 'Medical Insurance', 5.0),
('HOUSE', 'House Allowance', 14.0),
('TRANSPORT', 'Transport Allowance', 14.0),
('OTHERS', 'Other Deductions', 5.0)
ON CONFLICT (deduction_name) DO UPDATE
SET percentage = EXCLUDED.percentage; 