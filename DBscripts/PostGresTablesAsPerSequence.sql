

#Run all create table scripts  as per sequence which is mentioned below #
CREATE TABLE IF NOT EXISTS public.lntds_vendors
(
    vendor_seq integer NOT NULL DEFAULT 0, -- Or any other default value you prefer
    vendor_name character varying(128) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    description character varying(4000) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    vendor_address character varying(512) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    create_user character varying(32) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    create_timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_user character varying(32) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    update_timestamp timestamp without time zone,
    CONSTRAINT lntds_vendors_pkey PRIMARY KEY (vendor_seq)
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_vendors
    OWNER to postgres;
	
	
	CREATE TABLE IF NOT EXISTS public.lntds_asset_types
(
    asset_type_seq integer NOT NULL,
    asset_type_name character varying(40) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    asset_type_ver character varying(4) COLLATE pg_catalog."default" DEFAULT '1.0'::character varying,
    vendor_seq integer NOT NULL,
    symbol character varying(255) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    description character varying(4000) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    image_symbol bytea,
    is_virtual character(1) COLLATE pg_catalog."default" DEFAULT 'N'::bpchar,
    create_user character varying(32) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    create_timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_user character varying(32) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    update_timestamp timestamp without time zone,
    CONSTRAINT lntds_asset_types_pkey PRIMARY KEY (asset_type_seq),
    CONSTRAINT fk_dt_vendor FOREIGN KEY (vendor_seq)
        REFERENCES public.lntds_vendors (vendor_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_asset_types
    OWNER to postgres;
	
	
	CREATE TABLE IF NOT EXISTS public.lntds_asset_types_measurements
(
    id integer NOT NULL,
    asset_type_seq integer,
    measurement_name character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    display_measurement_name character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    asset_tag_name character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    measurement_class character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    measurement_type character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    uom character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    consumption_meas_name character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    generate_consumption_data character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    consumption_offset double precision,
    create_user character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    create_date timestamp without time zone,
    update_user character varying(64) COLLATE pg_catalog."default" DEFAULT NULL::character varying,
    update_date timestamp without time zone,
    CONSTRAINT lntds_asset_types_measurements_pkey PRIMARY KEY (id),
    CONSTRAINT idx_uniq_device_type_meas UNIQUE (asset_type_seq, measurement_name, asset_tag_name),
    CONSTRAINT fk_measurement_device_types FOREIGN KEY (asset_type_seq)
        REFERENCES public.lntds_asset_types (asset_type_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_asset_types_measurements
    OWNER to postgres;
	
	
	
CREATE SEQUENCE IF NOT EXISTS lntds_asset_group_type_id_seq;

CREATE TABLE IF NOT EXISTS public.lntds_asset_group_type
(
    id integer NOT NULL DEFAULT nextval('lntds_asset_group_type_id_seq'::regclass),
    type_name character varying(45) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT lntds_asset_group_type_pkey PRIMARY KEY (id)
);

ALTER TABLE IF EXISTS public.lntds_asset_group_type
    OWNER to postgres;
	
	
	CREATE TABLE IF NOT EXISTS public.lntds_asset_groups
(
    group_seq integer NOT NULL ,
    group_name character varying(64) COLLATE pg_catalog."default",
    latitude character varying(32) COLLATE pg_catalog."default",
    longitude character varying(32) COLLATE pg_catalog."default",
    parent_group_seq integer,
    description text COLLATE pg_catalog."default",
    altitude character varying(32) COLLATE pg_catalog."default",
    geo_x numeric(10,0),
    geo_y numeric(10,0),
    default_yn character(1) COLLATE pg_catalog."default" DEFAULT 'N'::bpchar,
    xmin_val numeric(10,0),
    xmax_val numeric(10,0),
    ymin numeric(10,0),
    umax numeric(10,0),
    create_user character varying(32) COLLATE pg_catalog."default",
    create_timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_user character varying(32) COLLATE pg_catalog."default",
    update_timestamp timestamp without time zone,
    group_type integer,
    CONSTRAINT lntds_asset_groups_pkey PRIMARY KEY (group_seq),
    CONSTRAINT lntds_asset_groups_group_type_fkey FOREIGN KEY (group_type)
        REFERENCES public.lntds_asset_group_type (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lntds_asset_groups_parent_group_seq_fkey FOREIGN KEY (parent_group_seq)
        REFERENCES public.lntds_asset_groups (group_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;



	CREATE TABLE IF NOT EXISTS public.lntds_assets
(
    asset_id character varying(64) COLLATE pg_catalog."default" NOT NULL,
    system_interface_seq integer,
    adapter_seq integer,
    asset_class character varying(64) COLLATE pg_catalog."default",
    facility_id integer,
    asset_ipaddr character varying(39) COLLATE pg_catalog."default",
    asset_port integer,
    asset_access_type character varying(64) COLLATE pg_catalog."default",
    asset_userid character varying(32) COLLATE pg_catalog."default",
    asset_password character varying(1024) COLLATE pg_catalog."default",
    asset_iot_conn_str character varying(1024) COLLATE pg_catalog."default",
    asset_name character varying(64) COLLATE pg_catalog."default",
    asset_alias character varying(64) COLLATE pg_catalog."default",
    description character varying(4000) COLLATE pg_catalog."default",
    location character varying(255) COLLATE pg_catalog."default",
    parent_comm_asset_id character varying(64) COLLATE pg_catalog."default",
    parent_elec_asset_id character varying(64) COLLATE pg_catalog."default",
    asset_record_ver character varying(4) COLLATE pg_catalog."default" DEFAULT '1.0'::character varying,
    installation_date date,
    registration_ts timestamp without time zone,
    provisioning_ts timestamp without time zone,
    group_seq integer DEFAULT 1,
    vendor_seq integer NOT NULL,
    asset_type_seq integer NOT NULL,
    latitude character varying(32) COLLATE pg_catalog."default",
    longitude character varying(32) COLLATE pg_catalog."default",
    geo_x numeric(10,0),
    geo_y numeric(10,0),
    altitude character varying(32) COLLATE pg_catalog."default",
    data_multiplier double precision DEFAULT 1,
    attribute01 character varying(64) COLLATE pg_catalog."default",
    attribute02 character varying(64) COLLATE pg_catalog."default",
    attribute03 character varying(64) COLLATE pg_catalog."default",
    attribute04 character varying(64) COLLATE pg_catalog."default",
    attribute05 character varying(64) COLLATE pg_catalog."default",
    attribute06 character varying(64) COLLATE pg_catalog."default",
    attribute07 character varying(64) COLLATE pg_catalog."default",
    attribute08 character varying(64) COLLATE pg_catalog."default",
    attribute09 character varying(64) COLLATE pg_catalog."default",
    attribute10 character varying(64) COLLATE pg_catalog."default",
    attribute11 character varying(64) COLLATE pg_catalog."default",
    attribute12 character varying(64) COLLATE pg_catalog."default",
    attribute13 character varying(64) COLLATE pg_catalog."default",
    attribute14 character varying(64) COLLATE pg_catalog."default",
    attribute15 character varying(64) COLLATE pg_catalog."default",
    attribute16 character varying(64) COLLATE pg_catalog."default",
    attribute17 character varying(64) COLLATE pg_catalog."default",
    attribute18 character varying(64) COLLATE pg_catalog."default",
    attribute19 character varying(64) COLLATE pg_catalog."default",
    attribute20 character varying(64) COLLATE pg_catalog."default",
    attribute21 character varying(64) COLLATE pg_catalog."default",
    attribute22 character varying(64) COLLATE pg_catalog."default",
    attribute23 character varying(64) COLLATE pg_catalog."default",
    attribute24 character varying(64) COLLATE pg_catalog."default",
    attribute25 character varying(64) COLLATE pg_catalog."default",
    attribute26 character varying(64) COLLATE pg_catalog."default",
    attribute27 character varying(64) COLLATE pg_catalog."default",
    attribute28 character varying(64) COLLATE pg_catalog."default",
    attribute29 character varying(64) COLLATE pg_catalog."default",
    attribute30 character varying(64) COLLATE pg_catalog."default",
    attribute31 character varying(64) COLLATE pg_catalog."default",
    attribute32 character varying(64) COLLATE pg_catalog."default",
    attribute33 character varying(64) COLLATE pg_catalog."default",
    attribute34 character varying(64) COLLATE pg_catalog."default",
    attribute35 character varying(64) COLLATE pg_catalog."default",
    attribute36 character varying(64) COLLATE pg_catalog."default",
    attribute37 character varying(64) COLLATE pg_catalog."default",
    attribute38 character varying(64) COLLATE pg_catalog."default",
    attribute39 character varying(64) COLLATE pg_catalog."default",
    attribute40 character varying(64) COLLATE pg_catalog."default",
    attribute41 character varying(64) COLLATE pg_catalog."default",
    attribute42 character varying(64) COLLATE pg_catalog."default",
    attribute43 character varying(64) COLLATE pg_catalog."default",
    attribute44 character varying(64) COLLATE pg_catalog."default",
    attribute45 character varying(64) COLLATE pg_catalog."default",
    attribute46 character varying(64) COLLATE pg_catalog."default",
    attribute47 character varying(64) COLLATE pg_catalog."default",
    attribute48 character varying(64) COLLATE pg_catalog."default",
    attribute49 character varying(64) COLLATE pg_catalog."default",
    attribute50 character varying(64) COLLATE pg_catalog."default",
    create_user character varying(32) COLLATE pg_catalog."default",
    create_timestamp timestamp without time zone DEFAULT CURRENT_TIMESTAMP,
    update_user character varying(32) COLLATE pg_catalog."default",
    update_timestamp timestamp without time zone,
    asset_tz character varying(32) COLLATE pg_catalog."default",
    used_in_optimization character(1) COLLATE pg_catalog."default" DEFAULT 'N'::bpchar,
    controllable_asset character(1) COLLATE pg_catalog."default" DEFAULT 'N'::bpchar,
    is_virtual character(1) COLLATE pg_catalog."default" DEFAULT 'N'::bpchar,
    system_interface character varying(45) COLLATE pg_catalog."default",
    protocol character varying(45) COLLATE pg_catalog."default",
    adapter character varying(45) COLLATE pg_catalog."default",
    CONSTRAINT lntds_assets_pkey PRIMARY KEY (asset_id),
    CONSTRAINT lntds_assets_asset_type_seq_fkey FOREIGN KEY (asset_type_seq)
        REFERENCES public.lntds_asset_types (asset_type_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lntds_assets_group_seq_fkey FOREIGN KEY (group_seq)
        REFERENCES public.lntds_asset_groups (group_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lntds_assets_parent_comm_asset_id_fkey FOREIGN KEY (parent_comm_asset_id)
        REFERENCES public.lntds_assets (asset_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lntds_assets_parent_elec_asset_id_fkey FOREIGN KEY (parent_elec_asset_id)
        REFERENCES public.lntds_assets (asset_id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT lntds_assets_vendor_seq_fkey FOREIGN KEY (vendor_seq)
        REFERENCES public.lntds_vendors (vendor_seq) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_assets
    OWNER to postgres;
	
	
	CREATE SEQUENCE IF NOT EXISTS lntds_latest_data_id_seq;
	
	
	
	CREATE TABLE IF NOT EXISTS public.lntds_latest_data
(
    id integer NOT NULL DEFAULT nextval('lntds_latest_data_id_seq'::regclass),
    asset_id character varying(255) COLLATE pg_catalog."default",
    input_measurement_name character varying(255) COLLATE pg_catalog."default",
    measurement_name character varying(255) COLLATE pg_catalog."default",
    measurement_input_value double precision,
    measurement_type character varying(255) COLLATE pg_catalog."default",
    measurement_value double precision,
    record_timestamp timestamp(6) without time zone,
    unit_of_measurement character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT lntds_latest_data_pkey PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_latest_data
    OWNER to postgres;
	
	
	CREATE TABLE IF NOT EXISTS public.lntds_discrete_data
(
    asset_id character varying(64) COLLATE pg_catalog."default" NOT NULL,
    meas_id integer NOT NULL,
    record_timestamp timestamp with time zone NOT NULL,
    measurement_discrete_value double precision,
    CONSTRAINT lntds_discrete_data_pkey PRIMARY KEY (asset_id, meas_id, record_timestamp),
    CONSTRAINT fk_lntds_discrete_data_meas_id FOREIGN KEY (meas_id)
        REFERENCES public.lntds_asset_types_measurements (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_discrete_data
    OWNER to postgres;
	
	
	
	CREATE TABLE IF NOT EXISTS public.lntds_analog_data
(
    asset_id character varying(64) COLLATE pg_catalog."default" NOT NULL,
    meas_id integer NOT NULL,
    record_timestamp timestamp with time zone NOT NULL,
    measurement_analog_value double precision,
    CONSTRAINT lntds_analog_data_pkey PRIMARY KEY (asset_id, meas_id, record_timestamp),
    CONSTRAINT fk_lntds_analog_data_meas_id FOREIGN KEY (meas_id)
        REFERENCES public.lntds_asset_types_measurements (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.lntds_analog_data
    OWNER to postgres;
	
