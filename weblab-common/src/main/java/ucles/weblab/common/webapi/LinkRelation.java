package ucles.weblab.common.webapi;

/**
 * Constants for link relations, used with {@link org.springframework.hateoas.core.LinkBuilderSupport#withRel(String)}
 * when creating Spring HATEOAS relations.
 * <p>See <a href='http://www.iana.org/assignments/link-relations/link-relations.xhtml'>IANA Link Relation Types</a></p>
 */
@SuppressWarnings("UnusedDeclaration")
public enum LinkRelation {
    /**
     * Refers to a resource that is the subject of the link's context.
     * Ref: <a href='http://www.iana.org/go/rfc6903'>RFC6903</a>, section 2.
     */
    ABOUT,
    /**
     * Refers to a substitute for this context.
     * Ref: <a href='http://www.w3.org/TR/html5/links.html#link-type-alternate'>http://www.w3.org/TR/html5/links.html#link-type-alternate</a>
     */
    ALTERNATE,
    /**
     * Refers to an appendix.
     * Ref: <a href='http://www.w3.org/TR/1999/REC-html401-19991224'>http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    APPENDIX,
    /**
     * Refers to a collection of records, documents, or other materials of historical interest.
     * Ref: <a href="http://www.w3.org/TR/2011/WD-html5-20110113/links.html#rel-archives">http://www.w3.org/TR/2011/WD-html5-20110113/links.html#rel-archives</a>
     */
    ARCHIVES,
    /**
     * Refers to the context's author.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-author">http://www.w3.org/TR/html5/links.html#link-type-author</a>
     */
    AUTHOR,
    /**
     * Gives a permanent link to use for bookmarking purposes.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-bookmark">http://www.w3.org/TR/html5/links.html#link-type-bookmark</a>
     */
    BOOKMARK,
    /**
     * Designates the preferred version of a resource (the IRI and its contents).
     * Ref: <a href="http://www.iana.org/go/rfc6596">RFC6596</a>
     */
    CANONICAL,
    /**
     * Refers to a chapter in a collection of resources.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    CHAPTER,
    /**
     * The target IRI points to a resource which represents the collection resource for the context IRI.
     * Ref: <a href="http://www.iana.org/go/rfc6573">RFC6573</a>
     */
    COLLECTION,
    /**
     * Refers to a table of contents.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    CONTENTS,
    /**
     * Refers to a copyright statement that applies to the link's context.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    COPYRIGHT,
    /**
     * Refers to a target to use for creating new instances of a schema.
     * This link definition SHOULD be a submission link with a non-safe method (like POST).
     * Ref: <a href="https://tools.ietf.org/html/draft-zyp-json-schema-03#section-6.1.1">draft-zyp-json-schema-03</a>
     */
    CREATE,
    /**
     * The target IRI points to a resource where a submission form can be obtained.
     * Ref: <a href="http://www.iana.org/go/rfc6861">RFC6861</a>
     */
    CREATE_FORM("create-form"),
    /**
     * Refers to a resource containing the most recent item(s) in a collection of resources.
     * Ref: <a href="http://www.iana.org/go/rfc5005">RFC5005</a>
     */
    CURRENT,
    /**
     * Refers to a resource providing information about the link's context.
     * Ref: <a href="http://www.w3.org/TR/powder-dr/#assoc-linking">http://www.w3.org/TR/powder-dr/#assoc-linking</a>
     */
    DESCRIBED_BY("describedby"),
    /**
     * The relationship A 'describes' B asserts that resource A provides a description of resource B. There are no constraints on the format or representation of either A or B, neither are there any further constraints on either resource.
     * Ref: <a href="http://www.iana.org/go/rfc6892">RFC6892</a>
     */
    DESCRIBES,
    /**
     * Refers to a list of patent disclosures made with respect to material for which 'disclosure' relation is specified.
     * Ref: <a href="http://www.iana.org/go/rfc6579">RFC6579</a>
     */
    DISCLOSURE,
    /**
     * Refers to a resource whose available representations are byte-for-byte identical with the corresponding representations of the context IRI.
     * Ref: <a href="http://www.iana.org/go/rfc6249">RFC6249</a>
     */
    DUPLICATE,
    /**
     * Refers to a resource that can be used to edit the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc5023">RFC5023</a>
     */
    EDIT,
    /**
     * The target IRI points to a resource where a submission form for editing associated resource can be obtained.
     * Ref: <a href="http://www.iana.org/go/rfc6861">RFC6861</a>
     */
    EDIT_FORM("edit-form"),
    /**
     * Refers to a resource that can be used to edit media associated with the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc5023">RFC5023</a>
     */
    EDIT_MEDIA("edit-media"),
    /**
     * Identifies a related resource that is potentially large and might require special handling.
     * Ref: <a href="http://www.iana.org/go/rfc4287">RFC4287</a>
     */
    ENCLOSURE,
    /**
     * An IRI that refers to the furthest preceding resource in a series of resources.
     * Ref: <a href="http://www.iana.org/go/rfc5988">RFC5988</a>
     */
    FIRST,
    /**
     * Refers to a glossary of terms.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    GLOSSARY,
    /**
     * Refers to context-sensitive help.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-help">http://www.w3.org/TR/html5/links.html#link-type-help</a>
     */
    HELP,
    /**
     * Refers to a resource hosted by the server indicated by the link context.
     * Ref: <a href="http://www.iana.org/go/rfc6690">RFC6690</a>
     */
    HOSTS,
    /**
     * Refers to a hub that enables registration for notification of updates to the context.
     * Ref: <a href="http://pubsubhubbub.googlecode.com">http://pubsubhubbub.googlecode.com</a>
     */
    HUB,
    /**
     * Refers to an icon representing the link's context.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-icon">http://www.w3.org/TR/html5/links.html#link-type-icon</a>
     */
    ICON,
    /**
     * Refers to an index.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    INDEX,
    /**
     * Refers to the target resource that represents collection of instances of a schema.
     * Ref: <a href="https://tools.ietf.org/html/draft-zyp-json-schema-03#section-6.1.1">draft-zyp-json-schema-03</a>
     */
    INSTANCES,
    /**
     * The target IRI points to a resource that is a member of the collection represented by the context IRI.
     * Ref: <a href="http://www.iana.org/go/rfc6573">RFC6573</a>
     */
    ITEM,
    /**
     * An IRI that refers to the furthest following resource in a series of resources.
     * Ref: <a href="http://www.iana.org/go/rfc5988">RFC5988</a>
     */
    LAST,
    /**
     * Points to a resource containing the latest (e.g., current) version of the context.
     * Ref: <a href="http://www.iana.org/go/rfc5829">RFC5829</a>
     */
    LATEST_VERSION("latest-version"),
    /**
     * Refers to a license associated with this context.
     * Ref: <a href="http://www.iana.org/go/rfc4946">RFC4946</a>
     */
    LICENSE,
    /**
     * Refers to further information about the link's context, expressed as a LRDD ("Link-based Resource Descriptor Document") resource.
     * Ref: <a href="http://www.iana.org/go/rfc6415">RFC6415</a>
     */
    LRDD,
    /**
     * The Target IRI points to a Memento, a fixed resource that will not change state anymore.
     * Ref: <a href="http://www.iana.org/go/rfc7089">RFC7089</a>
     */
    MEMENTO,
    /**
     * Refers to a resource that can be used to monitor changes in an HTTP resource.
     * Ref: <a href="http://www.iana.org/go/rfc5989">RFC5989</a>
     */
    MONITOR,
    /**
     * Refers to a resource that can be used to monitor changes in a specified group of HTTP resources.
     * Ref: <a href="http://www.iana.org/go/rfc5989">RFC5989</a>
     */
    MONITOR_GROUP("monitor-group"),
    /**
     * Indicates that the link's context is a part of a series, and that the next in the series is the link target.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-next">http://www.w3.org/TR/html5/links.html#link-type-next</a>
     */
    NEXT,
    /**
     * Refers to the immediately following archive resource.
     * Ref: <a href="http://www.iana.org/go/rfc5005">RFC5005</a>
     */
    NEXT_ARCHIVE("next-archive"),
    /**
     * Indicates that the context’s original author or publisher does not endorse the link target.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-nofollow">http://www.w3.org/TR/html5/links.html#link-type-nofollow</a>
     */
    NOFOLLOW,
    /**
     * Indicates that no referrer information is to be leaked when following the link.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-noreferrer">http://www.w3.org/TR/html5/links.html#link-type-noreferrer</a>
     */
    NOREFERRER,
    /**
     * The Target IRI points to an Original Resource.
     * Ref: <a href="http://www.iana.org/go/rfc7089">RFC7089</a>
     */
    ORIGINAL,
    /**
     * Indicates a resource where payment is accepted.
     * Ref: <a href="http://www.iana.org/go/rfc5988">RFC5988</a>
     */
    PAYMENT,
    /**
     * Points to a resource containing the predecessor version in the version history.
     * Ref: <a href="http://www.iana.org/go/rfc5829">RFC5829</a>
     */
    PREDECESSOR_VERSION("predecessor-version"),
    /**
     * Indicates that the link target should be preemptively cached.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-prefetch">http://www.w3.org/TR/html5/links.html#link-type-prefetch</a>
     */
    PREFETCH,
    /**
     * Indicates that the link's context is a part of a series, and that the previous in the series is the link target.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-prev">http://www.w3.org/TR/html5/links.html#link-type-prev</a>
     */
    PREV,
    /**
     * Refers to a resource that provides a preview of the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc6903">RFC6903</a>, section 3
     */
    PREVIEW,
    /**
     * Refers to the previous resource in an ordered series of resources. Synonym for "prev".
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    PREVIOUS,
    /**
     * Refers to the immediately preceding archive resource.
     * Ref: <a href="http://www.iana.org/go/rfc5005">RFC5005</a>
     */
    PREV_ARCHIVE("prev-archive"),
    /**
     * Refers to a privacy policy associated with the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc6903">RFC6903</a>, section 4
     */
    PRIVACY_POLICY("privacy-policy"),
    /**
     * Identifying that a resource representation conforms to a certain profile, without affecting the non-profile semantics of the resource representation.
     * Ref: <a href="http://www.iana.org/go/rfc6906">RFC6906</a>
     */
    PROFILE,
    /**
     * Identifies a related resource.
     * Ref: <a href="http://www.iana.org/go/rfc4287">RFC4287</a>
     */
    RELATED,
    /**
     * Identifies a resource that is a reply to the context of the link.
     * Ref: <a href="http://www.iana.org/go/rfc4685">RFC4685</a>
     */
    REPLIES,
    /**
     * Refers to a resource that can be used to search through the link's context and related resources.
     * Ref: <a href="http://www.opensearch.org/Specifications/OpenSearch/1.1">http://www.opensearch.org/Specifications/OpenSearch/1.1</a>
     */
    SEARCH,
    /**
     * Refers to a section in a collection of resources.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    SECTION,
    /**
     * Conveys an identifier for the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc4287">RFC4287</a>
     */
    SELF,
    /**
     * Indicates a URI that can be used to retrieve a service document.
     * Ref: <a href="http://www.iana.org/go/rfc5023">RFC5023</a>
     */
    SERVICE,
    /**
     * Refers to the first resource in a collection of resources.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    START,
    /**
     * Refers to a stylesheet.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-stylesheet">http://www.w3.org/TR/html5/links.html#link-type-stylesheet</a>
     */
    STYLESHEET,
    /**
     * Refers to a resource serving as a subsection in a collection of resources.
     * Ref: <a href="http://www.w3.org/TR/1999/REC-html401-19991224">http://www.w3.org/TR/1999/REC-html401-19991224</a>
     */
    SUBSECTION,
    /**
     * Points to a resource containing the successor version in the version history.
     * Ref: <a href="http://www.iana.org/go/rfc5829">RFC5829</a>
     */
    SUCCESSOR_VERSION("successor-version"),
    /**
     * Gives a tag (identified by the given address) that applies to the current document.
     * Ref: <a href="http://www.w3.org/TR/html5/links.html#link-type-tag">http://www.w3.org/TR/html5/links.html#link-type-tag</a>
     */
    TAG,
    /**
     * Refers to the terms of service associated with the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc6903">RFC6903</a>, section 5
     */
    TERMS_OF_SERVICE("terms-of-service"),
    /**
     * The Target IRI points to a TimeGate for an Original Resource.
     * Ref: <a href="http://www.iana.org/go/rfc7089">RFC7089</a>
     */
    TIMEGATE,
    /**
     * The Target IRI points to a TimeMap for an Original Resource.
     * Ref: <a href="http://www.iana.org/go/rfc7089">RFC7089</a>
     */
    TIMEMAP,
    /**
     * Refers to a resource identifying the abstract semantic type of which the link's context is considered to be an instance.
     * Ref: <a href="http://www.iana.org/go/rfc6903">RFC6903</a>, section 6
     */
    TYPE,
    /**
     * Refers to a parent document in a hierarchy of documents.
     * Ref: <a href="http://www.iana.org/go/rfc5988">RFC5988</a>
     */
    UP,
    /**
     * Points to a resource containing the version history for the context.
     * Ref: <a href="http://www.iana.org/go/rfc5829">RFC5829</a>
     */
    VERSION_HISTORY("version-history"),
    /**
     * Identifies a resource that is the source of the information in the link's context.
     * Ref: <a href="http://www.iana.org/go/rfc4287">RFC4287</a>
     */
    VIA,
    /**
     * Points to a working copy for this resource.
     * Ref: <a href="http://www.iana.org/go/rfc5829">RFC5829</a>
     */
    WORKING_COPY("working-copy"),
    /**
     * Points to the versioned resource from which this working copy was obtained.
     * Ref: <a href="http://www.iana.org/go/rfc5829">RFC5829</a>
     */
    WORKING_COPY_OF("working-copy-of");

    private final String relOverride;

    /**
     * If the relation name is the same as the enum constant value (but lowercase), no override is needed.
     */
    private LinkRelation() {
        this.relOverride = null;
    }

    /**
     * If the relation name is difference to the enum constant value, then this constructor allows it to be overridden.
     *
     * @param relOverride the relation name to return from {@link #rel()}
     */
    private LinkRelation(String relOverride) {
        this.relOverride = relOverride;
    }

    public String rel() {
        return relOverride == null ? name().toLowerCase() : relOverride;
    }

}
