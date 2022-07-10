PGDMP     /                     z           sqlDB    14.4    14.4 2    3           0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false            4           0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false            5           0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false            6           1262    16708    sqlDB    DATABASE     k   CREATE DATABASE "sqlDB" WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE = 'English_United States.1252';
    DROP DATABASE "sqlDB";
                postgres    false                        2615    16709 
   sql_schema    SCHEMA        CREATE SCHEMA sql_schema;
    DROP SCHEMA sql_schema;
                postgres    false            P           1247    16957    period_rent    TYPE     g   CREATE TYPE public.period_rent AS ENUM (
    '1 week',
    ' 2 weeks',
    '3 weeks',
    '1 month'
);
    DROP TYPE public.period_rent;
       public          postgres    false            �            1259    16982    authors    TABLE     �   CREATE TABLE public.authors (
    author_id integer NOT NULL,
    author_firstname character varying(50),
    author_lastname character varying(50),
    genre character varying(10)
);
    DROP TABLE public.authors;
       public         heap    postgres    false            �            1259    16981    authors_author_id_seq    SEQUENCE     �   ALTER TABLE public.authors ALTER COLUMN author_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.authors_author_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    221            �            1259    16988    authors_book    TABLE     �   CREATE TABLE public.authors_book (
    author_book_id integer NOT NULL,
    author_id integer NOT NULL,
    book_id integer NOT NULL
);
     DROP TABLE public.authors_book;
       public         heap    postgres    false            �            1259    16987    authors_book_author_book_id_seq    SEQUENCE     �   ALTER TABLE public.authors_book ALTER COLUMN author_book_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.authors_book_author_book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    223            �            1259    16915    books    TABLE     �   CREATE TABLE public.books (
    book_id integer NOT NULL,
    book_title character varying(50),
    book_isbn bigint,
    book_owner character varying(50),
    is_rented boolean DEFAULT false,
    is_reserved boolean DEFAULT false
);
    DROP TABLE public.books;
       public         heap    postgres    false            �            1259    16914    books_book_id_seq    SEQUENCE     �   ALTER TABLE public.books ALTER COLUMN book_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.books_book_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    213            �            1259    16966    books_reservation    TABLE     �   CREATE TABLE public.books_reservation (
    book_reservation_id integer NOT NULL,
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    period_res public.period_rent NOT NULL,
    start_date date NOT NULL
);
 %   DROP TABLE public.books_reservation;
       public         heap    postgres    false    848            �            1259    16965 )   books_reservation_book_reservation_id_seq    SEQUENCE     �   ALTER TABLE public.books_reservation ALTER COLUMN book_reservation_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.books_reservation_book_reservation_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    219            �            1259    16941    rental    TABLE     �   CREATE TABLE public.rental (
    rental_id integer NOT NULL,
    user_id integer NOT NULL,
    book_id integer NOT NULL,
    start_period date NOT NULL,
    end_period date NOT NULL
);
    DROP TABLE public.rental;
       public         heap    postgres    false            �            1259    16940    rental_rental_id_seq    SEQUENCE     �   ALTER TABLE public.rental ALTER COLUMN rental_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.rental_rental_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    217            �            1259    16923    user_books_list    TABLE     u   CREATE TABLE public.user_books_list (
    book_list_id integer NOT NULL,
    user_id integer,
    book_id integer
);
 #   DROP TABLE public.user_books_list;
       public         heap    postgres    false            �            1259    16922     user_books_list_book_list_id_seq    SEQUENCE     �   ALTER TABLE public.user_books_list ALTER COLUMN book_list_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.user_books_list_book_list_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    215            �            1259    16909    users    TABLE       CREATE TABLE public.users (
    user_id integer NOT NULL,
    user_name character varying(50),
    user_address character varying(255),
    user_account_login character varying(30),
    user_account_password character varying(30),
    user_account_email character varying(50)
);
    DROP TABLE public.users;
       public         heap    postgres    false            �            1259    16908    users_user_id_seq    SEQUENCE     �   ALTER TABLE public.users ALTER COLUMN user_id ADD GENERATED ALWAYS AS IDENTITY (
    SEQUENCE NAME public.users_user_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1
);
            public          postgres    false    211            .          0    16982    authors 
   TABLE DATA           V   COPY public.authors (author_id, author_firstname, author_lastname, genre) FROM stdin;
    public          postgres    false    221   C;       0          0    16988    authors_book 
   TABLE DATA           J   COPY public.authors_book (author_book_id, author_id, book_id) FROM stdin;
    public          postgres    false    223   `;       &          0    16915    books 
   TABLE DATA           c   COPY public.books (book_id, book_title, book_isbn, book_owner, is_rented, is_reserved) FROM stdin;
    public          postgres    false    213   };       ,          0    16966    books_reservation 
   TABLE DATA           j   COPY public.books_reservation (book_reservation_id, user_id, book_id, period_res, start_date) FROM stdin;
    public          postgres    false    219   �;       *          0    16941    rental 
   TABLE DATA           W   COPY public.rental (rental_id, user_id, book_id, start_period, end_period) FROM stdin;
    public          postgres    false    217   �;       (          0    16923    user_books_list 
   TABLE DATA           I   COPY public.user_books_list (book_list_id, user_id, book_id) FROM stdin;
    public          postgres    false    215   �;       $          0    16909    users 
   TABLE DATA           �   COPY public.users (user_id, user_name, user_address, user_account_login, user_account_password, user_account_email) FROM stdin;
    public          postgres    false    211   �;       7           0    0    authors_author_id_seq    SEQUENCE SET     D   SELECT pg_catalog.setval('public.authors_author_id_seq', 1, false);
          public          postgres    false    220            8           0    0    authors_book_author_book_id_seq    SEQUENCE SET     N   SELECT pg_catalog.setval('public.authors_book_author_book_id_seq', 1, false);
          public          postgres    false    222            9           0    0    books_book_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.books_book_id_seq', 1, false);
          public          postgres    false    212            :           0    0 )   books_reservation_book_reservation_id_seq    SEQUENCE SET     X   SELECT pg_catalog.setval('public.books_reservation_book_reservation_id_seq', 1, false);
          public          postgres    false    218            ;           0    0    rental_rental_id_seq    SEQUENCE SET     C   SELECT pg_catalog.setval('public.rental_rental_id_seq', 1, false);
          public          postgres    false    216            <           0    0     user_books_list_book_list_id_seq    SEQUENCE SET     O   SELECT pg_catalog.setval('public.user_books_list_book_list_id_seq', 1, false);
          public          postgres    false    214            =           0    0    users_user_id_seq    SEQUENCE SET     @   SELECT pg_catalog.setval('public.users_user_id_seq', 1, false);
          public          postgres    false    210            �           2606    16992    authors_book authors_book_pkey 
   CONSTRAINT     h   ALTER TABLE ONLY public.authors_book
    ADD CONSTRAINT authors_book_pkey PRIMARY KEY (author_book_id);
 H   ALTER TABLE ONLY public.authors_book DROP CONSTRAINT authors_book_pkey;
       public            postgres    false    223            �           2606    16986    authors authors_pkey 
   CONSTRAINT     Y   ALTER TABLE ONLY public.authors
    ADD CONSTRAINT authors_pkey PRIMARY KEY (author_id);
 >   ALTER TABLE ONLY public.authors DROP CONSTRAINT authors_pkey;
       public            postgres    false    221            �           2606    16921    books books_book_isbn_key 
   CONSTRAINT     Y   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_book_isbn_key UNIQUE (book_isbn);
 C   ALTER TABLE ONLY public.books DROP CONSTRAINT books_book_isbn_key;
       public            postgres    false    213            �           2606    16919    books books_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.books
    ADD CONSTRAINT books_pkey PRIMARY KEY (book_id);
 :   ALTER TABLE ONLY public.books DROP CONSTRAINT books_pkey;
       public            postgres    false    213            �           2606    16970 (   books_reservation books_reservation_pkey 
   CONSTRAINT     w   ALTER TABLE ONLY public.books_reservation
    ADD CONSTRAINT books_reservation_pkey PRIMARY KEY (book_reservation_id);
 R   ALTER TABLE ONLY public.books_reservation DROP CONSTRAINT books_reservation_pkey;
       public            postgres    false    219            �           2606    16945    rental rental_pkey 
   CONSTRAINT     W   ALTER TABLE ONLY public.rental
    ADD CONSTRAINT rental_pkey PRIMARY KEY (rental_id);
 <   ALTER TABLE ONLY public.rental DROP CONSTRAINT rental_pkey;
       public            postgres    false    217            �           2606    16927 $   user_books_list user_books_list_pkey 
   CONSTRAINT     l   ALTER TABLE ONLY public.user_books_list
    ADD CONSTRAINT user_books_list_pkey PRIMARY KEY (book_list_id);
 N   ALTER TABLE ONLY public.user_books_list DROP CONSTRAINT user_books_list_pkey;
       public            postgres    false    215            �           2606    16913    users users_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public.users
    ADD CONSTRAINT users_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public.users DROP CONSTRAINT users_pkey;
       public            postgres    false    211            �           2606    16993    authors_book fk_authors    FK CONSTRAINT     �   ALTER TABLE ONLY public.authors_book
    ADD CONSTRAINT fk_authors FOREIGN KEY (author_id) REFERENCES public.authors(author_id);
 A   ALTER TABLE ONLY public.authors_book DROP CONSTRAINT fk_authors;
       public          postgres    false    223    221    3213            �           2606    16933    user_books_list fk_books    FK CONSTRAINT     |   ALTER TABLE ONLY public.user_books_list
    ADD CONSTRAINT fk_books FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 B   ALTER TABLE ONLY public.user_books_list DROP CONSTRAINT fk_books;
       public          postgres    false    215    3205    213            �           2606    16951    rental fk_books    FK CONSTRAINT     s   ALTER TABLE ONLY public.rental
    ADD CONSTRAINT fk_books FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 9   ALTER TABLE ONLY public.rental DROP CONSTRAINT fk_books;
       public          postgres    false    217    213    3205            �           2606    16976    books_reservation fk_books    FK CONSTRAINT     ~   ALTER TABLE ONLY public.books_reservation
    ADD CONSTRAINT fk_books FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 D   ALTER TABLE ONLY public.books_reservation DROP CONSTRAINT fk_books;
       public          postgres    false    213    219    3205            �           2606    16998    authors_book fk_books    FK CONSTRAINT     y   ALTER TABLE ONLY public.authors_book
    ADD CONSTRAINT fk_books FOREIGN KEY (book_id) REFERENCES public.books(book_id);
 ?   ALTER TABLE ONLY public.authors_book DROP CONSTRAINT fk_books;
       public          postgres    false    3205    223    213            �           2606    16928    user_books_list fk_users    FK CONSTRAINT     |   ALTER TABLE ONLY public.user_books_list
    ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 B   ALTER TABLE ONLY public.user_books_list DROP CONSTRAINT fk_users;
       public          postgres    false    211    3201    215            �           2606    16946    rental fk_users    FK CONSTRAINT     s   ALTER TABLE ONLY public.rental
    ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 9   ALTER TABLE ONLY public.rental DROP CONSTRAINT fk_users;
       public          postgres    false    3201    217    211            �           2606    16971    books_reservation fk_users    FK CONSTRAINT     ~   ALTER TABLE ONLY public.books_reservation
    ADD CONSTRAINT fk_users FOREIGN KEY (user_id) REFERENCES public.users(user_id);
 D   ALTER TABLE ONLY public.books_reservation DROP CONSTRAINT fk_users;
       public          postgres    false    3201    211    219            .      x������ � �      0      x������ � �      &      x������ � �      ,      x������ � �      *      x������ � �      (      x������ � �      $      x������ � �     