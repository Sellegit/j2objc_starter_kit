# the sole purpose of this makefile is to serve as a temporary hack before
#   building patterns are properly abstracted and probably integrated to Gradle
#

default: xcode

PROJECTS = APPIOS

.PHONY: $(PROJECTS)

PROJECT_PATH = ..
APP_NAME = appname
GENERATED_DEST = $(APP_NAME)/generated
MANIFEST_DEST = build_temp

$(MANIFEST_DEST):
	mkdir $(MANIFEST_DEST)

$(GENERATED_DEST):
	mkdir $(GENERATED_DEST)

APPIOS = $(PROJECT_PATH)/$(APP_NAME).ios

empty :=
space := $(empty) $(empty)
comma := ,

getsourcedir = $(1)/src/main/java
gettestsourcedir = $(1)/src/test/java
# getsources = $(wildcard $(call getsourcedir,$(1))/**/*.java)
getsources = $(shell find $(call getsourcedir,$(1)) -name "*.java")
gettestsources = $(shell find $(call gettestsourcedir,$(1)) -name "*.java")
getmanifest = $(MANIFEST_DEST)/$(1).manifest
getclasspaths = $(or $(subst $(space),:,$(foreach pj,$(1),$(call getsourcedir,$($(pj))))),.)
getdests = $(subst java,m,$(subst $(call getsourcedir,$(1)),$(GENERATED_DEST),$(shell find $(call getsourcedir,$(1)) -name "*.java")))
getdestandsourcepairs = $(foreach source,$(call getsources,$(1)),$(subst java,m,$(subst $(call getsourcedir,$(1)),$(GENERATED_DEST),$(source)))$(comma)$(source))

compile = j2objc -cp $(call getclasspaths,$($(1)_DEPENDSON)) \
	-sourcepath $(call getsourcedir,$($(1))) \
        -use-arc \
	-d $(GENERATED_DEST) \
	$(call getsources,$($(1)))

SYNC_MANIFEST = $(call getmanifest,SYNC)
$(SYNC_MANIFEST): $(shell find $(GENERATED_DEST) -name "*.m") $(shell find $(GENERATED_DEST) -name "*.h")
	integratej2objc integrate_source -x $(APP_NAME).xcodeproj -s $(APP_NAME)/generated -g "$(APP_NAME)/generated" -t $(APP_NAME) --files=$(APP_NAME)/main.m
	touch $(SYNC_MANIFEST)

xcode: sync_app

sync_app: APPIOS
	make $(SYNC_MANIFEST)

sync_incremental: $(SYNC_MANIFEST)

define make_rule
$(call getmanifest,$(1)): $(call getsources,$($(1))) $(foreach pj,$($(1)_DEPENDSON),$(call getmanifest,$(pj))) | $(MANIFEST_DEST)
	$(call compile,$(1))
	touch $(call getmanifest,$(1))

$(1): $(call getmanifest,$(1))

endef

$(foreach pj,$(PROJECTS),$(eval $(call make_rule,$(pj))))

all_classpaths := $(call getclasspaths,$(PROJECTS))
all_dests := $(foreach pj,$(PROJECTS),$(call getdests,$($(pj))))

define make_individual_rule
list = $(subst $(comma),$(space),$(1))
dest = $(word 1,$(list))
source = $(word 2,$(list))

$(dest): $(source)
	j2objc -cp $(all_classpaths) \
        -use-arc \
	-d $(GENERATED_DEST) \
	$(source)
endef


$(foreach pj,$(PROJECTS),$(foreach pair,$(call getdestandsourcepairs,$($(pj))),$(eval $(call make_individual_rule,$(pair)))))

sourcepaths:
	@echo $(call getclasspaths,$(PROJECTS))

incremental: $(all_dests) sync_incremental
	@echo done

clean:
	rm -f $(MANIFEST_DEST)/*
	rm -rf $(GENERATED_DEST)/*

