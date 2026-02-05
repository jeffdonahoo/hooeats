import psycopg2

DB_CONFIG = {
    'host': 'localhost',
    'port': 5432,
    'dbname': 'restaurant',
    'user': 'manager',
    'password': 'password123'
}

def print_menu(conn):
    print("=== HooEats Menu ===\n")

    with conn.cursor() as cur:
        cur.execute("SELECT name, price FROM items ORDER BY name")
        rows = cur.fetchall()

        if not rows:
            print("No menu items.")
        else:
            for name, price in rows:
                price_str = f"${price}" if price is not None else "$?"
                print(f"{name} - {price_str}")

def create_item(conn):
    item_id = input("Item ID (5 chars max): ").strip()
    name = input("Name (30 chars max): ").strip()
    price_input = input("Price: ").strip()
    price = float(price_input) if price_input else None
    date_input = input("Date Added (YYYY-MM-DD, blank for no date): ").strip()
    date_added = date_input if date_input else None

    with conn.cursor() as cur:
        cur.execute(
            "INSERT INTO items (itemid, name, price, dateadded) VALUES (%s, %s, %s, %s)",
            (item_id, name, price, date_added)
        )
    conn.commit()
    print("Item created successfully.")

def main():
    try:
        with psycopg2.connect(**DB_CONFIG) as conn:
            print_menu(conn)

            response = input("\nCreate new item? (y/n): ").strip().lower()
            if response == 'y':
                create_item(conn)

    except psycopg2.Error as e:
        print(f"Database error: {e}")

if __name__ == "__main__":
    main()
